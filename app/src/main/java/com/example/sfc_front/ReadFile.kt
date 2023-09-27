package com.example.sfc_front

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import kotlin.system.exitProcess
import java.io.File


class MyAdapter(private val data: List<String>,private val iconResourceId: Int) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val fileName: TextView = itemView.findViewById(R.id.file_name) // 通过ID找到文本视图
        val icon: ImageView = itemView.findViewById(R.id.view_icon) // 通过ID找到图标视图

        init {
            // 在这里初始化 icon，如果需要的话
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
        holder.fileName.setOnClickListener {
            Toast.makeText(holder.itemView.context, "123", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}

fun listFilesInDirectory(directoryPath: File, fileExtension: String): List<String> {
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
        if (file.isFile && file.name.endsWith(fileExtension)) {
            fileNames.add(file.name)
        }
    }

    return fileNames
}


class ReadFile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_gallery)
        val goBack = findViewById<ImageButton>(R.id.goBack)
        goBack.setOnClickListener{
            finish()
        }
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val topPadding = resources.getDimensionPixelSize(R.dimen.top_padding) // 从资源文件获取内边距值
        recyclerView.setPadding(50, topPadding, 0, 0)
        // 准备模拟的数据集合
        val data = listOf("Item 1", "Item 2", "Item 3", "Item 4", "Item 5","Item 6","Item 7","Item 8","Item 9","Item 10","Item 11","Item 12","Item 13","Item 14","Item 15","Item 16")

        // 创建 RecyclerView 的适配器并设置数据


        // 设置 RecyclerView 的布局管理器，例如 LinearLayoutManager
        recyclerView.layoutManager = LinearLayoutManager(this)
        val directoryPath = getExternalFilesDir(null) // 替换为你要读取的目录路径

        val fileNames = directoryPath?.let { listFilesInDirectory(it,".png") }
        val adapter = fileNames?.let { MyAdapter(it,R.drawable.photo_file) }
        recyclerView.adapter = adapter
        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.navigation_fragment_image_button -> {
                    // 处理点击 item1 的逻辑
                    // 这里可以执行相应的操作
                    Toast.makeText(this, "Item 1 Clicked", Toast.LENGTH_SHORT).show()
                    val fileNames = directoryPath?.let { listFilesInDirectory(it,".png") }
                    val adapter = fileNames?.let { MyAdapter(it,R.drawable.photo_file) }
                    recyclerView.adapter = adapter
                    true
                }
                R.id.navigation_video_button -> {
                    // 处理点击 item2 的逻辑
                    // 这里可以执行相应的操作
                    Toast.makeText(this, "Item 2 Clicked", Toast.LENGTH_SHORT).show()
                    val fileNames = directoryPath?.let { listFilesInDirectory(it,".mp4") }
                    val adapter = fileNames?.let { MyAdapter(it,R.drawable.video_file) }
                    recyclerView.adapter = adapter
                    true
                }
                R.id.navigation_audio_button -> {
                    // 处理点击 item2 的逻辑
                    // 这里可以执行相应的操作
                    Toast.makeText(this, "Item 3 Clicked", Toast.LENGTH_SHORT).show()
                    val fileNames = directoryPath?.let { listFilesInDirectory(it,".mp3") }
                    val adapter = fileNames?.let { MyAdapter(it,R.drawable.music_file) }
                    recyclerView.adapter = adapter
                    true
                }
                R.id.navigation_text_file_button -> {
                    // 处理点击 item2 的逻辑
                    // 这里可以执行相应的操作
                    Toast.makeText(this, "Item 4 Clicked", Toast.LENGTH_SHORT).show()
                    val fileNames = directoryPath?.let { listFilesInDirectory(it,".txt") }
                    val adapter = fileNames?.let { MyAdapter(it,R.drawable.txt_file) }
                    recyclerView.adapter = adapter
                    true
                }
                R.id.navigation_file_button -> {
                    // 处理点击 item2 的逻辑
                    // 这里可以执行相应的操作
                    Toast.makeText(this, "Item 5 Clicked", Toast.LENGTH_SHORT).show()
                    val fileNames = directoryPath?.let { listFilesInDirectory(it,"") }
                    val adapter = fileNames?.let { MyAdapter(it,R.drawable.file_file) }
                    recyclerView.adapter = adapter
                    true
                }
                // 添加其他 item 的处理逻辑
                else -> false
            }
        }
    }

}
