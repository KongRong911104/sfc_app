package com.example.sfc_front

import android.os.Bundle
import android.os.StrictMode
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.io.File


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
        val bottomPadding = resources.getDimensionPixelSize(R.dimen.bottom_padding)
        recyclerView.setPadding(50, topPadding, 0, bottomPadding)
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
                val adapter = fileNames?.let { MyAdapter(it, R.drawable.photo_file, this@ReadFile,directoryPath = directoryPath, fileType = fileType) }
                recyclerView.adapter = adapter
            }

            override fun afterTextChanged(p0: Editable?) {
                // Not implemented
            }
        })
        val fileNames = directoryPath?.let { listFilesInDirectory(it, userInput, ".png") }
        val adapter = fileNames?.let { MyAdapter(it, R.drawable.photo_file, this@ReadFile,directoryPath = directoryPath, fileType = ".png") }
        recyclerView.adapter = adapter
        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.navigation_fragment_image_button -> {
                    fileType = ".png"
                    val fileNames = directoryPath?.let { listFilesInDirectory(it, userInput, ".png") }
                    val adapter = fileNames?.let { MyAdapter(it, R.drawable.photo_file, this@ReadFile,directoryPath = directoryPath, fileType = ".png") }
                    if (adapter != null) {
                        adapter.setFileOpenCallback(object : MyAdapter.FileOpenCallback {
                            override fun onFileOpenCompleted(file: File) {
                                // 在这里执行文件打开操作完成后的逻辑
                                // 例如，刷新数据或执行其他操作
                                Log.e("test","delete file")
                                file.delete()
                            }

                        })
                    }
                    recyclerView.adapter = adapter
                    true
                }
                R.id.navigation_video_button -> {
                    fileType = ".mp4"
                    val fileNames = directoryPath?.let { listFilesInDirectory(it, userInput, ".mp4") }
                    val adapter = fileNames?.let { MyAdapter(it, R.drawable.video_file, this@ReadFile,directoryPath = directoryPath, fileType = ".mp4") }
                    recyclerView.adapter = adapter
                    true
                }
                R.id.navigation_audio_button -> {
                    fileType = ".mp3"
                    val fileNames = directoryPath?.let { listFilesInDirectory(it, userInput, ".mp3") }
                    val adapter = fileNames?.let { MyAdapter(it, R.drawable.music_file, this@ReadFile,directoryPath = directoryPath, fileType = ".mp3") }
                    recyclerView.adapter = adapter
                    true
                }
                R.id.navigation_text_file_button -> {
                    fileType = ".txt"
                    val fileNames = directoryPath?.let { listFilesInDirectory(it, userInput, ".txt") }
                    val adapter = fileNames?.let { MyAdapter(it, R.drawable.txt_file, this@ReadFile,directoryPath = directoryPath, fileType = ".txt") }
                    recyclerView.adapter = adapter
                    true
                }
                R.id.navigation_file_button -> {
                    fileType = ""
                    val fileNames = directoryPath?.let { listFilesInDirectory(it, userInput, "") }
                    val adapter = fileNames?.let { MyAdapter(it, R.drawable.file_file, this@ReadFile,directoryPath = directoryPath, fileType = "") }
                    recyclerView.adapter = adapter
                    true
                }
                else -> false
            }

        }
    }
}
