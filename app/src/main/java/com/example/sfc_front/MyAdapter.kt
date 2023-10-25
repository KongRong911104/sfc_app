package com.example.sfc_front

import android.content.Intent
import android.os.Environment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.sfc_front.ui.AES.AES256
import com.example.sfc_front.ui.FDAES.FDAES
import java.io.File

fun listFilesInDirectory(
    directoryPath: File,
    input: String,
    fileExtension: String,
    open: Int = 1
): List<String> {
    val directory = directoryPath
    var fileExtension2 : String

    if (fileExtension == ".png") {

        fileExtension2 = ".jpg"
    } else {
        fileExtension2 = fileExtension
    }
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
//        Log.e("nonono", userInput.toString())
        if (file.isFile && userInput && (file.name.endsWith(fileExtension) || file.name.endsWith(fileExtension2))) {
            if (open == 1 && file.name.contains("Encrypted")) {
                fileNames.add(file.name)
            } else if (open == 0 && file.name.contains("AES_Encrypted") && !file.name.contains("FDAES_Encrypted")) {
                fileNames.add(file.name)
            }
        }

    }

    return fileNames
}

class MyAdapter(
    private var data: List<String>,
    private val iconResourceId: Int,
    private val context: AppCompatActivity,
    private val open: Int = 1,
    private val directoryPath: File,
    private val fileType: String
) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {
    val fdaes = FDAES("sixsquare1234567")
    val aes256 = AES256("sixsquare1234567")

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val fileName: TextView = itemView.findViewById(R.id.file_name) // 通过ID找到文本视图
        val icon: ImageView = itemView.findViewById(R.id.view_icon) // 通过ID找到图标视图

        init {
            if (open == 1) {
                // 在这里初始化 icon，如果需要的话
                itemView.setOnClickListener {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        val FileName = data[position]
                        val fileToOpen = File(context.getExternalFilesDir(null), FileName)

                        if (FileName.contains("FDAES_Encrypted")) {

                            val subString: String =
                                FileName.subSequence(16, FileName.length) as String
                            val outputFile = File(context.getExternalFilesDir(null), subString)
                            fdaes.FileDecryption_CBC(fileToOpen, outputFile)
                            openFile(outputFile, context)
                            updateData(listFilesInDirectory(directoryPath, "", fileType, open))
                        } else if (FileName.contains("AES_Encrypted")) {
                            val subString: String =
                                FileName.subSequence(14, FileName.length) as String
                            val outputFile = File(context.getExternalFilesDir(null), subString)
                            aes256.decryptFile(fileToOpen, outputFile)
                            openFile(outputFile, context)
                            updateData(listFilesInDirectory(directoryPath, "", fileType, open))
                        }


                    }

                }
            } else if (open == 0) {
                itemView.setOnClickListener {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        val string = data[position]
                        val fileToOpen = File(context.getExternalFilesDir(null), string)
                        val subString: String = string.subSequence(14, string.length) as String
                        val outputFile = File(context.getExternalFilesDir(null), subString)
                        aes256.decryptFile(fileToOpen, outputFile)
                        fileToOpen.delete()
                        val fdaesOutputFile =
                            File(context.getExternalFilesDir(null), "FDAES_Encrypted_$subString")
                        fdaes.FileEncryption_CBC(outputFile, fdaesOutputFile)
                        outputFile.delete()
                        updateData(listFilesInDirectory(directoryPath, "", fileType, open))
                        Toast.makeText(context, "加密完成", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)

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

    private fun updateData(newData: List<String>) {
        data = newData
        notifyDataSetChanged()
    }

    fun listFilesInDirectory(
        directoryPath: File,
        input: String,
        fileExtension: String,
        open: Int = 1
    ): List<String> {

        val directory = directoryPath
        var fileExtension2 : String

        if (fileExtension == ".png") {

            fileExtension2 = ".jpg"
        } else {
            fileExtension2 = fileExtension
        }

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
//        Log.e("nonono", userInput.toString())
            if (file.isFile && userInput && (file.name.endsWith(fileExtension) || file.name.endsWith(
                    fileExtension2
                ))
            ) {
                if (open == 1 && file.name.contains("Encrypted")) {
                    fileNames.add(file.name)
                } else if (open == 0 && (file.name.endsWith(fileExtension) || file.name.endsWith(fileExtension2)) && file.name.contains("AES_Encrypted") && !file.name.contains("FDAES_Encrypted")) {
//                    Log.e("test0","$open")
//                    Log.e("test1","${file.name.endsWith(fileExtension)}")
//                    Log.e("test2","${file.name.endsWith(fileExtension2)}")
//                    Log.e("test3","${file.name.contains("AES_Encrypted")}")
                    fileNames.add(file.name)
                }
            }

        }

        return fileNames
    }

}
