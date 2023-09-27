package com.example.sfc_front

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.StrictMode
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.core.content.FileProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.io.File
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class MyAdapter(private val data: List<String>, private val iconResourceId: Int, private val context: AppCompatActivity) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val fileName: TextView = itemView.findViewById(R.id.file_name) // 通过ID找到文本视图
        val icon: ImageView = itemView.findViewById(R.id.view_icon) // 通过ID找到图标视图

        init {
            // 在这里初始化 icon，如果需要的话
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val fileToOpen = File(context.getExternalFilesDir(null), data[position])
                    openFile(fileToOpen, context)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)

        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.fileName.text = item
        holder.icon.setImageResource(iconResourceId)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    private fun openFile(file: File, context: AppCompatActivity) {
        val uri = FileProvider.getUriForFile(context, context.packageName + ".fileprovider", file)
        val mime = context.contentResolver.getType(uri)
        val intent = Intent(Intent.ACTION_VIEW)
        intent.setDataAndType(uri, mime)
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        try {
            context.startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(context, "無法開啟檔案", Toast.LENGTH_SHORT).show()
        }
    }
}

fun listFilesInDirectory(directoryPath: File, input: String, fileExtension: String): List<String> {
    val directory = directoryPath

    // 检查目录是否存在
    if (!directory.exists() || !directory.isDirectory) {
        return emptyList()
    }

    // 使用 listFiles() 方法获取目录下的所有文件
    val files = directory.listFiles()

    // 如果没有文件，返回空列表
    if (files == null || files.isEmpty()) {
        return emptyList()
    }

    // 提取文件名并添加到列表中
    val fileNames = mutableListOf<String>()
    for (file in files) {
        val userInput = file.name.contains(input, true)
        if (file.isFile && userInput && file.name.endsWith(fileExtension)) {
            fileNames.add(file.name)
        }
    }

    return fileNames
}

class ReadFile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_gallery)

        // 建立 FileProvider 的授權
        val builder = StrictMode.VmPolicy.Builder()
        StrictMode.setVmPolicy(builder.build())

        val goBack = findViewById<ImageButton>(R.id.goBack)
        goBack.setOnClickListener {
            finish()
        }
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val topPadding = resources.getDimensionPixelSize(R.dimen.top_padding) // 从资源文件获取内边距值
        recyclerView.setPadding(50, topPadding, 0, 0)
        // 准备模拟的数据集合
        val data = listOf(
            "Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 6", "Item 7", "Item 8", "Item 9", "Item 10",
            "Item 11", "Item 12", "Item 13", "Item 14", "Item 15", "Item 16"
        )
        var fileType = ".png" // 初始檔案類型
        // 创建 RecyclerView 的适配器并设置数据

        // 设置 RecyclerView 的布局管理器，例如 LinearLayoutManager
        recyclerView.layoutManager = LinearLayoutManager(this)
        val directoryPath = getExternalFilesDir(null) // 替换为你要读取的目录路径
        val searchFile = findViewById<EditText>(R.id.search_file)
        var userInput = ""
        searchFile.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // Not implemented
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                userInput = s.toString()
                val fileNames = directoryPath?.let { listFilesInDirectory(it, userInput, fileType) }
                val adapter = fileNames?.let { MyAdapter(it, R.drawable.photo_file, this@ReadFile) }
                recyclerView.adapter = adapter
            }

            override fun afterTextChanged(p0: Editable?) {
                // Not implemented
            }
        })
        val fileNames = directoryPath?.let { listFilesInDirectory(it, userInput, ".png") }
        val adapter = fileNames?.let { MyAdapter(it, R.drawable.photo_file, this) }
        recyclerView.adapter = adapter
        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.navigation_fragment_image_button -> {
                    fileType = ".png"
                    val fileNames = directoryPath?.let { listFilesInDirectory(it, userInput, ".png") }
                    val adapter = fileNames?.let { MyAdapter(it, R.drawable.photo_file, this) }
                    recyclerView.adapter = adapter
                    true
                }
                R.id.navigation_video_button -> {
                    fileType = ".mp4"
                    val fileNames = directoryPath?.let { listFilesInDirectory(it, userInput, ".mp4") }
                    val adapter = fileNames?.let { MyAdapter(it, R.drawable.video_file, this) }
                    recyclerView.adapter = adapter
                    true
                }
                R.id.navigation_audio_button -> {
                    fileType = ".mp3"
                    val fileNames = directoryPath?.let { listFilesInDirectory(it, userInput, ".mp3") }
                    val adapter = fileNames?.let { MyAdapter(it, R.drawable.music_file, this) }
                    recyclerView.adapter = adapter
                    true
                }
                R.id.navigation_text_file_button -> {
                    fileType = ".txt"
                    val fileNames = directoryPath?.let { listFilesInDirectory(it, userInput, ".txt") }
                    val adapter = fileNames?.let { MyAdapter(it, R.drawable.txt_file, this) }
                    recyclerView.adapter = adapter
                    true
                }
                R.id.navigation_file_button -> {
                    fileType = ""
                    val fileNames = directoryPath?.let { listFilesInDirectory(it, userInput, "") }
                    val adapter = fileNames?.let { MyAdapter(it, R.drawable.file_file, this) }
                    recyclerView.adapter = adapter
                    true
                }
                else -> false
            }
        }
    }
}
