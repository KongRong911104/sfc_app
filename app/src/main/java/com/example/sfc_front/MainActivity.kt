package com.example.sfc_front

import com.example.sfc_front.ui.AES.AES256
import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.provider.OpenableColumns
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import android.widget.ImageButton
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.core.app.ActivityCompat
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.sfc_front.databinding.ActivityMainBinding
import com.example.sfc_front.ui.FDAES.FDAES
import com.google.android.material.navigation.NavigationView
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.concurrent.Executors
import kotlin.system.exitProcess
import android.widget.EditText
import com.example.sfc_front.ui.library.library

import com.example.sfc_front.SwitchStatus
import com.example.sfc_front.ui.home.HomeFragment
import com.example.sfc_front.ui.home.NoteActivity
import java.util.concurrent.Executor

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.text.InputType
import android.widget.ProgressBar



import com.example.sfc_front.ui.library.JsonFileManager

//import kotlinx.coroutines.scheduling.DefaultScheduler.executor
//
//import kotlinx.coroutines.scheduling.DefaultIoScheduler.executor

class MainActivity : AppCompatActivity() {
    val aes256 = AES256("sixsquare1234567")
    val fdaes = FDAES("sixsquare1234567")
    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var promptInfo: BiometricPrompt.PromptInfo
    private var failAuthentication = 0

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var takePictureLauncher: ActivityResultLauncher<Uri>
    private var FileName =""

    private val password = ""
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val ball = findViewById<ProgressBar>(R.id.progressBar)
        val ballText = findViewById<TextView>(R.id.ball_text)
        setSupportActionBar(binding.appBarMain.toolbar)
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)
//        var rootView = findViewById<View>(android.R.id.content)
        val switch:Switch = findViewById(R.id.switchButton)
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home,R.id.nav_slideshow,R.id.nav_gallery
            ), drawerLayout
        )

        switch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked){
                Toast.makeText(this, "Cloud Backup in Progress.", Toast.LENGTH_SHORT).show()
//                val executor: Executor = Executors.newSingleThreadExecutor()
//
//                // 創建生物識別驗證對話框
//                biometricPrompt = BiometricPrompt(this, executor,
//                    object : BiometricPrompt.AuthenticationCallback() {
//                        override fun onAuthenticationError(
//                            errorCode: Int,
//                            errString: CharSequence
//                        ) {
//                            super.onAuthenticationError(errorCode, errString)
//                            runOnUiThread {
//
//                                Toast.makeText(
//                                    applicationContext,
//                                    "Authentication error:  $errString", Toast.LENGTH_SHORT
//                                ).show()
//                                moveTaskToBack(true);
//                                exitProcess(-1)
//                            }
//                        }
//
//                        override fun onAuthenticationSucceeded(
//                            result: BiometricPrompt.AuthenticationResult
//                        ) {
//                            super.onAuthenticationSucceeded(result)
//                            runOnUiThread {
////                                Toast.makeText(
////                                    applicationContext,
////                                    "Authentication succeeded!", Toast.LENGTH_SHORT
////                                ).show()
//                                showInputDialog(
//                                    this@MainActivity,
//                                    "Please Enter Your Password",
//                                    "Confirm",
//                                    "Cancel",
//                                    { userInput ->
//                                        // 用户点击确定按钮后的处理逻辑，userInput 包含用户输入的文本
//                                        // 在这里添加你的代码
////                                        Toast.makeText(this@MainActivity, "$userInput", Toast.LENGTH_SHORT).show()
//                                        Toast.makeText(this@MainActivity, "Authentication succeeded!", Toast.LENGTH_SHORT).show()
//
//                                    },
//                                    {
//                                        // 用户点击取消按钮后的处理逻辑
//                                    }
//                                )
//
//
//                            }
//                        }
//
//                        override fun onAuthenticationFailed() {
//                            super.onAuthenticationFailed()
//                            runOnUiThread {
//
//                                Toast.makeText(
//                                    applicationContext, "Authentication failed",
//                                    Toast.LENGTH_SHORT
//                                ).show()
//                                failAuthentication += 1
//                                if (failAuthentication == 3){
//                                    moveTaskToBack(true);
//                                    exitProcess(-1)
//                                }
//                            }
//                        }
//                    })
//                promptInfo = BiometricPrompt.PromptInfo.Builder()
//                    .setTitle("Confirm Using Your Fingerprint")
//                    .setSubtitle("You can use your fingerprint to confirm making payments through this app.")
//                    .setAllowedAuthenticators(
//                        BiometricManager.Authenticators.BIOMETRIC_WEAK
//                    )
//                    .setNegativeButtonText("Exit")
//
//                    .build()
//
//                // 開始生物識別驗證
//                biometricPrompt.authenticate(promptInfo)

            }
            else{
                Toast.makeText(this, "Cloud Backup has been turned off", Toast.LENGTH_SHORT).show()
            }
        }
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        val noteButton = findViewById<ImageButton>(R.id.note_button)
        noteButton.setOnClickListener{
            val intent = Intent(this, NoteActivity::class.java)
            val s = switch.isChecked
            intent.putExtra("status", s)
            Log.e("test","$s")
            startActivity(intent)
        }
        val takePictureButton = findViewById<ImageButton>(R.id.camera_button)
        // 设置点击事件，调用拍照方法
        takePictureButton.setOnClickListener {
            takeAPhoto()
        }
        val takeVideoButton = findViewById<ImageButton>(R.id.video_button)
        takeVideoButton.setOnClickListener {
            takeAVideo()
        }
        val readFileButton = findViewById<ImageButton>(R.id.read_file_button)
        readFileButton.setOnClickListener {

            val intent = Intent(this, ReadFile::class.java)
            val executor: Executor = Executors.newSingleThreadExecutor()

                // 創建生物識別驗證對話框
                biometricPrompt = BiometricPrompt(this, executor,
                    object : BiometricPrompt.AuthenticationCallback() {
                        override fun onAuthenticationError(
                            errorCode: Int,
                            errString: CharSequence
                        ) {
                            super.onAuthenticationError(errorCode, errString)
                            runOnUiThread {

                                Toast.makeText(
                                    applicationContext,
                                    "Authentication error:  $errString", Toast.LENGTH_SHORT
                                ).show()
//                                moveTaskToBack(true);
//                                exitProcess(-1)
                            }
                        }
                        override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                            super.onAuthenticationSucceeded(result)
                            runOnUiThread {
                                val maxAttempts = 3 // 最大尝试次数
                                var time = JsonFileManager.readJsonFile(this@MainActivity).getInt("IncorrectPasswordAttempts")

                                if (time < maxAttempts) {
                                    showInputDialog(
                                        this@MainActivity,
                                        "Please Enter Your Password",
                                        "Confirm",
                                        "Cancel",
                                        { userInput ->
                                            if (userInput == password) {
                                                JsonFileManager.updateJsonKey(this@MainActivity, "IncorrectPasswordAttempts", "0")
                                                Toast.makeText(this@MainActivity, "Authentication succeeded!", Toast.LENGTH_SHORT).show()
                                                startActivity(intent)
                                            } else {
                                                Toast.makeText(this@MainActivity, "Authentication failed!", Toast.LENGTH_SHORT).show()
                                                JsonFileManager.updateJsonKey(this@MainActivity, "IncorrectPasswordAttempts", (time + 1).toString())
                                                time = JsonFileManager.readJsonFile(this@MainActivity).getInt("IncorrectPasswordAttempts")
                                                // 如果尝试次数未达到最大次数，再次显示密码输入对话框
                                                if (time < maxAttempts) {
                                                    onAuthenticationSucceeded(result)
                                                } else {
                                                    Toast.makeText(this@MainActivity, "Exceeded maximum attempts. Your phone has been locked.", Toast.LENGTH_SHORT).show()
                                                }
                                            }
                                        },
                                        {
                                            // 用户点击取消按钮后的处理逻辑
                                        }
                                    )
                                } else {
                                    Toast.makeText(this@MainActivity, "Your phone has been locked.", Toast.LENGTH_SHORT).show()
                                }
                            }
                        }

//                        override fun onAuthenticationSucceeded(
//                            result: BiometricPrompt.AuthenticationResult
//                        ) {
//                            super.onAuthenticationSucceeded(result)
//                            runOnUiThread {
////                                Toast.makeText(
////                                    applicationContext,
////                                    "Authentication succeeded!", Toast.LENGTH_SHORT
////                                ).show()
//                                val time = JsonFileManager.readJsonFile(this@MainActivity).getInt("IncorrectPasswordAttempts")
//                                if(time<3) {
//                                    showInputDialog(
//                                        this@MainActivity,
//                                        "Please Enter Your Password",
//                                        "Confirm",
//                                        "Cancel",
//                                        { userInput ->
//                                            // 用户点击确定按钮后的处理逻辑，userInput 包含用户输入的文本
//                                            // 在这里添加你的代码
//                                            if (userInput == password) {
//                                                JsonFileManager.updateJsonKey(this@MainActivity,"IncorrectPasswordAttempts","0")
//                                                Toast.makeText(
//                                                    this@MainActivity,
//                                                    "Authentication succeeded!",
//                                                    Toast.LENGTH_SHORT
//                                                ).show()
//                                                startActivity(intent)
//                                            }
////                                        Toast.makeText(this@MainActivity, "$userInput", Toast.LENGTH_SHORT).show()
//                                            else {
//                                                Toast.makeText(
//                                                    this@MainActivity,
//                                                    "Authentication failed!",
//                                                    Toast.LENGTH_SHORT
//                                                ).show()
//                                                JsonFileManager.updateJsonKey(this@MainActivity,"IncorrectPasswordAttempts",(time+1).toString())
//                                            }
//                                        },
//                                        {
//                                            // 用户点击取消按钮后的处理逻辑
//                                        }
//                                    )
//                                }
//                                else{
//                                    Toast.makeText(
//                                        this@MainActivity,
//                                        "your phone has been locked",
//                                        Toast.LENGTH_SHORT
//                                    ).show()
//                                }
//
//                            }
//                        }

                        override fun onAuthenticationFailed() {
                            super.onAuthenticationFailed()
                            runOnUiThread {

                                Toast.makeText(
                                    applicationContext, "Authentication failed",
                                    Toast.LENGTH_SHORT
                                ).show()
                                failAuthentication += 1
                                if (failAuthentication == 3){
//                                    moveTaskToBack(true);
//                                    exitProcess(-1)
                                }
                            }
                        }
                    })
                promptInfo = BiometricPrompt.PromptInfo.Builder()
                    .setTitle("Confirm Using Your Fingerprint")
                    .setSubtitle("You can use your fingerprint to confirm making payments through this app.")
                    .setAllowedAuthenticators(
                        BiometricManager.Authenticators.BIOMETRIC_WEAK or BiometricManager.Authenticators.BIOMETRIC_STRONG
                    )
                    .setNegativeButtonText("Exit")

                    .build()

                // 開始生物識別驗證
                biometricPrompt.authenticate(promptInfo)




//            val intent = Intent(this, ReadFile::class.java)
//            startActivity(intent)
        }
        val fileUpgradeButton = findViewById<ImageButton>(R.id.file_upgrade_button)
        fileUpgradeButton.setOnClickListener {

            val executor: Executor = Executors.newSingleThreadExecutor()
            val intent = Intent(this, FileUpgrade::class.java)


            // 創建生物識別驗證對話框
            biometricPrompt = BiometricPrompt(this, executor,
                object : BiometricPrompt.AuthenticationCallback() {
                    override fun onAuthenticationError(
                        errorCode: Int,
                        errString: CharSequence
                    ) {
                        super.onAuthenticationError(errorCode, errString)
                        runOnUiThread {

                            Toast.makeText(
                                applicationContext,
                                "Authentication error:  $errString", Toast.LENGTH_SHORT
                            ).show()
//                                moveTaskToBack(true);
//                                exitProcess(-1)
                        }
                    }

                    override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                        super.onAuthenticationSucceeded(result)
                        runOnUiThread {
                            val maxAttempts = 3 // 最大尝试次数
                            var time = JsonFileManager.readJsonFile(this@MainActivity).getInt("IncorrectPasswordAttempts")

                            if (time < maxAttempts) {
                                showInputDialog(
                                    this@MainActivity,
                                    "Please Enter Your Password",
                                    "Confirm",
                                    "Cancel",
                                    { userInput ->
                                        if (userInput == password) {
                                            JsonFileManager.updateJsonKey(this@MainActivity, "IncorrectPasswordAttempts", "0")
                                            Toast.makeText(this@MainActivity, "Authentication succeeded!", Toast.LENGTH_SHORT).show()
                                            startActivity(intent)
                                        } else {
                                            Toast.makeText(this@MainActivity, "Authentication failed!", Toast.LENGTH_SHORT).show()
                                            JsonFileManager.updateJsonKey(this@MainActivity, "IncorrectPasswordAttempts", (time + 1).toString())
                                            time = JsonFileManager.readJsonFile(this@MainActivity).getInt("IncorrectPasswordAttempts")
                                            // 如果尝试次数未达到最大次数，再次显示密码输入对话框
                                            if (time < maxAttempts) {
                                                onAuthenticationSucceeded(result)
                                            } else {
                                                Toast.makeText(this@MainActivity, "Exceeded maximum attempts. Your phone has been locked.", Toast.LENGTH_SHORT).show()
                                            }
                                        }
                                    },
                                    {
                                        // 用户点击取消按钮后的处理逻辑
                                    }
                                )
                            } else {
                                Toast.makeText(this@MainActivity, "Your phone has been locked.", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }

                    override fun onAuthenticationFailed() {
                        super.onAuthenticationFailed()
                        runOnUiThread {

                            Toast.makeText(
                                applicationContext, "Authentication failed",
                                Toast.LENGTH_SHORT
                            ).show()
                            failAuthentication += 1
                            if (failAuthentication == 3){
//                                    moveTaskToBack(true);
//                                    exitProcess(-1)
                            }
                        }
                    }
                })
            promptInfo = BiometricPrompt.PromptInfo.Builder()
                .setTitle("Confirm Using Your Face")
                .setSubtitle("You can use your face to confirm making payments through this app.")
                .setAllowedAuthenticators(
                    BiometricManager.Authenticators.BIOMETRIC_WEAK
                )
                .setNegativeButtonText("Exit")
                .build()

            // 開始生物識別驗證
            biometricPrompt.authenticate(promptInfo)
        }
        val goBack = findViewById<TextView>(R.id.logout)
        goBack.setOnClickListener {
            moveTaskToBack(true);
            exitProcess(-1)
        }
        val fileProtectButton : ImageButton = findViewById(R.id.file_protection_button)
        fileProtectButton.setOnClickListener{

            protectFile()
        }
        // 注册一个用于接收拍照结果的ActivityResultLauncher
        takePictureLauncher = registerForActivityResult(ActivityResultContracts.TakePicture()) { isTaken ->
            if (isTaken) {
                Toast.makeText(this, "Photo has been taken and saved", Toast.LENGTH_SHORT).show()

                val inputFile  = File(getExternalFilesDir(null), FileName)

                val executor = Executors.newSingleThreadExecutor()

                executor.execute {
                    try {
                        runOnUiThread {
                            ballText.setTextColor(Color.parseColor("#FFFFFFFF"))
                            ball.progressDrawable = resources.getDrawable(R.drawable.ball, null)
                        }
//                        val switch : Switch = findViewById<Switch>(R.id.switchButton)

//                        if (switch.isChecked){
//                            val outputFile=File(getExternalFilesDir(null),"FDAES_Encrypted_$FileName")
//                            fdaes.FileEncryption_CBC(inputFile,outputFile)
//                        }
//                        else{
                        val outputFile=File(getExternalFilesDir(null),"AES_Encrypted_$FileName")
                            // 在線程池中執行加密操作
                        aes256.encryptFile(inputFile, outputFile)
//                        }


                        // 刪除inputFile
                        if (inputFile.exists()) {
                            inputFile.delete()
                        }
                    } finally {
                        runOnUiThread {
                            ballText.setTextColor(Color.parseColor("#00FFFFFF"))
                            ball.progressDrawable = resources.getDrawable(R.drawable.logo, null)
                        }
                        executor.shutdown()
                    }
                }
            } else {
                Toast.makeText(this, "Unable to take a photo", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    private fun takeAPhoto() {
        // 检查相机权限
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), REQUEST_CAMERA_PERMISSION)
            return
        }

        // 创建保存照片的目录
        val photoDirectory = File(getExternalFilesDir(null), "")
        // 创建文件名
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val photoFileName = "IMG_$timeStamp.png"
        //等關閉後執行加密用
        FileName = photoFileName
        // 创建文件
        val photoFile = File(photoDirectory, photoFileName)

        val photoUri = FileProvider.getUriForFile(this, "com.example.sfc_front.fileprovider", photoFile)


        // 启动拍照 Intent
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
        // 启动拍照
        takePictureLauncher.launch(photoUri)

    }
    private fun takeAVideo() {
        // 检查相机和录制视频的权限
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED ||
            ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO), REQUEST_VIDEO_PERMISSION)
            return
        }

        // 创建保存视频的目录
        val videoDirectory = File(getExternalFilesDir(null), "")
        if (!videoDirectory.exists()) {
            videoDirectory.mkdirs()
        }

        // 创建文件名
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val videoFileName = "VID_$timeStamp.mp4"
        //等關閉後執行加密用
        FileName = videoFileName
        // 创建文件
        val videoFile = File(videoDirectory, videoFileName)

        val videoUri = FileProvider.getUriForFile(this, "com.example.sfc_front.fileprovider", videoFile)

        // 启动视频录制 Intent
        val takeVideoIntent = Intent(MediaStore.ACTION_VIDEO_CAPTURE)
        takeVideoIntent.putExtra(MediaStore.EXTRA_OUTPUT, videoUri)

        // 启动录制视频
        startActivityForResult(takeVideoIntent, REQUEST_VIDEO_CAPTURE)
    }

    private fun protectFile() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
            addCategory(Intent.CATEGORY_OPENABLE)
            type = "*/*"

            // Optionally, specify a URI for the file that should appear in the
            // system file picker when it loads.
//            putExtra(DocumentsContract.EXTRA_INITIAL_URI, pickerInitialUri)

        }

        startActivityForResult(intent, 2)
    }
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val ball = findViewById<ProgressBar>(R.id.progressBar)
        val ballText = findViewById<TextView>(R.id.ball_text)
        if (requestCode == REQUEST_VIDEO_CAPTURE && resultCode == Activity.RESULT_OK) {
            val inputFile  = File(getExternalFilesDir(null), FileName)

            val executor = Executors.newSingleThreadExecutor()
            executor.execute {
                try {

//                    val switch : Switch = findViewById<Switch>(R.id.switchButton)
                    runOnUiThread {
                    ballText.setTextColor(Color.parseColor("#FFFFFFFF"))
                    ball.progressDrawable = resources.getDrawable(R.drawable.ball, null)
                    }
//                    if (switch.isChecked){
//                        val outputFile=File(getExternalFilesDir(null),"FDAES_Encrypted_$FileName")
//                        fdaes.FileEncryption_CBC(inputFile,outputFile)
//                    }
//                    else{
                    val outputFile=File(getExternalFilesDir(null),"AES_Encrypted_$FileName")
                        // 在線程池中執行加密操作
                    aes256.encryptFile(inputFile, outputFile)
//                    }


                    // 刪除inputFile
                    if (inputFile.exists()) {
                        inputFile.delete()
                    }
                } finally {
                    runOnUiThread {
                        ballText.setTextColor(Color.parseColor("#00FFFFFF"))
                        ball.progressDrawable = resources.getDrawable(R.drawable.logo, null)
                    }
                    executor.shutdown()
                }
            }
        }
        else if (requestCode == HomeFragment.PICK_PDF_FILE && resultCode == Activity.RESULT_OK) {
            // 处理选择的文件，您可以使用选定的文件进行进一步操作
            val selectedFileUri = data?.data
            val FileName = selectedFileUri?.let { getFileNameFromUri(it) }
            if (selectedFileUri != null) {
                try {
                    val contentResolver = this.contentResolver
                    val inputStream = contentResolver.openInputStream(selectedFileUri)

                    if (inputStream != null) {
                        // 创建临时文件
                        val tempFile = createTempFile("temp_", ".tmp")

                        // 将输入流写入临时文件
                        inputStream.use { input ->
                            tempFile.outputStream().use { output ->
                                input.copyTo(output)
                            }
                        }

                        // 获取目标文件路径
                        val outputFile = File(this.getExternalFilesDir(null), "AES_Encrypted_$FileName")
                        // 执行加密操作
                        aes256.encryptFile(tempFile, outputFile)

                        // 删除临时文件
                        tempFile.delete()

                        // 关闭输入流
                        inputStream.close()
                    }
                } catch (e: Exception) {
                    Log.e("Error", "Error while processing file: ${e.message}")
                }

            }
        }


    }

    @SuppressLint("Range")
    fun getFileNameFromUri(uri: Uri): String? {
        var fileName: String? = null

        // 从 Uri 中提取文件名
        val cursor = this.contentResolver.query(uri, null, null, null, null)
        cursor?.use {
            if (it.moveToFirst()) {
                val displayName = it.getString(it.getColumnIndex(OpenableColumns.DISPLAY_NAME))
                if (!displayName.isNullOrEmpty()) {
                    fileName = displayName
                }
            }
        }

        return fileName
    }
    fun showInputDialog(context: Context, title: String, positiveButtonText: String, negativeButtonText: String, onPositiveClick: (String) -> Unit, onNegativeClick: () -> Unit) {
        val alertDialogBuilder = AlertDialog.Builder(context)
        val inputEditText = EditText(context)
        inputEditText.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD

        // 设置对话框标题
        alertDialogBuilder.setTitle(title)

        // 设置文本输入框
        alertDialogBuilder.setView(inputEditText)

        // 添加确定按钮
        alertDialogBuilder.setPositiveButton(positiveButtonText) { dialog, which ->
            val userInput = inputEditText.text.toString()

            onPositiveClick(userInput)
            dialog.dismiss()
        }


        // 添加取消按钮
        alertDialogBuilder.setNegativeButton(negativeButtonText) { dialog, which ->
            onNegativeClick()
            dialog.dismiss()
        }

        // 创建并显示对话框
        val alertDialog = alertDialogBuilder.create()

        // 设置按钮的颜色
        alertDialog.setOnShowListener { dialog ->
            val positiveButton = (dialog as AlertDialog).getButton(DialogInterface.BUTTON_POSITIVE)
            val negativeButton = dialog.getButton(DialogInterface.BUTTON_NEGATIVE)

            positiveButton.setTextColor(context.resources.getColor(R.color.dialog_button_positive_color))
            negativeButton.setTextColor(context.resources.getColor(R.color.dialog_button_negative_color))
        }


        alertDialog.show()
    }

    companion object {
        private const val REQUEST_CAMERA_PERMISSION = 101
        private const val REQUEST_VIDEO_PERMISSION = 102
        private const val REQUEST_VIDEO_CAPTURE = 103
    }
}
