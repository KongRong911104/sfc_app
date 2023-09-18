package com.example.sfc_front

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SignUp: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign_up) // 替换为您的布局文件

        val textView = findViewById<TextView>(R.id.sign_up)
        val fullText = getString(R.string.sign_up_title)

        // 定义要改变颜色的文字范围
        val start = fullText.indexOf("YOUR") // "YOUR" 文本的起始位置

        // 创建一个 SpannableString 来设置不同颜色
        val spannableString = SpannableString(fullText)

        // 设置 "YOUR" 文本的颜色为 #9A77D5
        val color = Color.parseColor("#9A77D5")
        spannableString.setSpan(
            ForegroundColorSpan(color),
            start,
            start + "YOUR".length, // 计算结束位置
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        // 将 SpannableString 设置为 TextView 的文本
        textView.text = spannableString
        val nameEditText = findViewById<EditText>(R.id.name_)
        val genderEditText = findViewById<EditText>(R.id.gender)
        val emailEditText = findViewById<EditText>(R.id.email)
        val passwordEditText = findViewById<EditText>(R.id.password)
        val retypePasswordEditText = findViewById<EditText>(R.id.retype_password)
        val phoneEditText = findViewById<EditText>(R.id.phone)
        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            val name = nameEditText.text.toString()
            val gender = genderEditText.text.toString()
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            val retypePassword = retypePasswordEditText.text.toString()
            val phone = phoneEditText.text.toString()

            if (name.isNotEmpty() && gender.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && retypePassword.isNotEmpty() && phone.isNotEmpty()) {
                if (password == retypePassword) {
                    val intent = Intent(this, Login::class.java)
                    startActivity(intent)
                } else {
                    val message = "password mismatch"
                    val duration = Toast.LENGTH_SHORT // 或 Toast.LENGTH_LONG，指定消息的显示时长
                    val toast = Toast.makeText(this, message, duration)
                    toast.show()
                }
            } else {
                val message = "value can't be null"
                val duration = Toast.LENGTH_SHORT // 或 Toast.LENGTH_LONG，指定消息的显示时长
                val toast = Toast.makeText(this, message, duration)
                toast.show()
            }
        }
    }
}
