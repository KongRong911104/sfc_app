package com.example.sfc_front.ui.home

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.os.Environment
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.example.sfc_front.R
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


        sendButton.setOnClickListener{

            try {


                Toast.makeText(this,fileName, Toast.LENGTH_SHORT).show()
                val file = File(getExternalFilesDir(null), "$fileName.txt")
                val fileWriter = FileWriter(file, true)
                fileWriter.write(content.toString())
                fileWriter.close()

            } catch (e: IOException) {
                e.printStackTrace()
            }
            finish()

        }
        cancelButton.setOnClickListener{
            finish()
        }

    }

}