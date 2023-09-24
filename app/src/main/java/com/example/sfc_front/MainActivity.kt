package com.example.sfc_front

import android.Manifest
import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.os.Handler
import android.os.Looper
import android.provider.MediaStore
import android.util.Log
import android.view.Menu
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.sfc_front.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var takePictureLauncher: ActivityResultLauncher<Uri>
    private lateinit var photoUri: Uri

//    companion object {
//        const val TAG = "CameraXApp"
//        private const val FILENAME_FORMAT = "yyyy-MM-dd-HH-mm-ss-SSS"
//        val REQUIRED_PERMISSIONS = mutableListOf(
//            Manifest.permission.CAMERA,
//            Manifest.permission.RECORD_AUDIO
//        ).apply {
//            if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.P) {
//                add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
//            }
//        }.toTypedArray()
//    }

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
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        val takePictureButton = findViewById<ImageButton>(R.id.camera_button)

        // 设置点击事件，调用拍照方法
        takePictureButton.setOnClickListener {
            takeAPhoto()
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
        val photoDirectory = File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), "photo")
        if (!photoDirectory.exists()) {
            photoDirectory.mkdirs()
        }

        // 创建文件名
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val photoFileName = "IMG_$timeStamp.png"

        // 创建文件
        val photoFile = File(photoDirectory, photoFileName)

        // 创建用于保存照片的 Uri
//        photoUri = Uri.fromFile(photoFile)
// 创建用于保存照片的 Uri，使用 FileProvider
        val photoUri = FileProvider.getUriForFile(this, "com.example.sfc_front.fileprovider", photoFile)

        // 启动拍照 Intent
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)


        // 启动拍照
        takePictureLauncher.launch(photoUri)
    }

    companion object {
        private const val REQUEST_CAMERA_PERMISSION = 101
    }
}
