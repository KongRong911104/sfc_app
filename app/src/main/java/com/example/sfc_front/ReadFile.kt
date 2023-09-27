package com.example.sfc_front

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import kotlin.system.exitProcess

class ReadFile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_gallery)
        val goBack = findViewById<ImageButton>(R.id.goBack)
        goBack.setOnClickListener{
            finish()
        }
    }
}
