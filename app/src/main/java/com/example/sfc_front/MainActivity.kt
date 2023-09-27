package com.example.sfc_front

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.Menu
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.sfc_front.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var takePictureLauncher: ActivityResultLauncher<Uri>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.appBarMain.toolbar)
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

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
            startActivity(intent)
        }
        val goBack = findViewById<TextView>(R.id.logout)
        goBack.setOnClickListener {
            moveTaskToBack(true);
            exitProcess(-1)
        }
        // 注册一个用于接收拍照结果的ActivityResultLauncher
        takePictureLauncher = registerForActivityResult(ActivityResultContracts.TakePicture()) { isTaken ->
            if (isTaken) {
                Toast.makeText(this, "Photo has been taken and saved", Toast.LENGTH_SHORT).show()
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

        // 创建文件
        val videoFile = File(videoDirectory, videoFileName)

        val videoUri = FileProvider.getUriForFile(this, "com.example.sfc_front.fileprovider", videoFile)

        // 启动视频录制 Intent
        val takeVideoIntent = Intent(MediaStore.ACTION_VIDEO_CAPTURE)
        takeVideoIntent.putExtra(MediaStore.EXTRA_OUTPUT, videoUri)

        // 启动录制视频
        startActivityForResult(takeVideoIntent, REQUEST_VIDEO_CAPTURE)
    }
    companion object {
        private const val REQUEST_CAMERA_PERMISSION = 101
        private const val REQUEST_VIDEO_PERMISSION = 102
        private const val REQUEST_VIDEO_CAPTURE = 103
    }
}
