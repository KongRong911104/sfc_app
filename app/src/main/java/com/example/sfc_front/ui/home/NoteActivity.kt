package com.example.sfc_front.ui.home

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.DocumentsContract
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.core.content.ContextCompat
//import com.example.sfc_front.Manifest
import com.example.sfc_front.R
import com.example.sfc_front.ui.AES.AES256
import com.example.sfc_front.ui.FDAES.FDAES
import java.io.File
import java.io.FileWriter
import java.io.IOException


class NoteActivity : ComponentActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.note_activity)
        val sendButton = findViewById<Button>(R.id.noteSendButton)
        val cancelButton = findViewById<Button>(R.id.noteCancelButton)
        val fileName = findViewById<EditText>(R.id.fileNameEditText).text
        val content = findViewById<EditText>(R.id.contentEditText).text
        when{
            ContextCompat.checkSelfPermission(this,android.Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(this,android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED -> {
                Toast.makeText(this, "granted", Toast.LENGTH_SHORT).show()
            }
            else -> {
                requestPermissions(arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE,android.Manifest.permission.READ_EXTERNAL_STORAGE),1)
//                request.launch(arrayOf(
//                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
//                    android.Manifest.permission.READ_EXTERNAL_STORAGE
//                ))
            }
        }
        sendButton.setOnClickListener{

            try {


                Toast.makeText(this,fileName, Toast.LENGTH_SHORT).show()
                val file = File(getExternalFilesDir(null), "$fileName.txt")
                val dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS)
//                val file = File(dir,"$fileName.txt")
                val fileWriter = FileWriter(file, true)
                fileWriter.write(content.toString())
                fileWriter.close()
                //加密
                val fdaes = FDAES("sixsquare1234567")
                val aes256 = AES256("sixsquare1234567")
                val outputFile=File(getExternalFilesDir(null),"Encrypted_$fileName.txt")
                aes256.encryptFile(file,outputFile)
                file.delete()
                //刪除原本檔案
            } catch (e: IOException) {
                e.printStackTrace()
            }
            finish()

        }
        cancelButton.setOnClickListener{
            finish()
        }
        fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<out String>,
            grantResults: IntArray
        ) {
            if (requestCode == 1) {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this, "success", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this, "need your permission", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
//    public fun requestPremission()
//    {
//
//    }

}