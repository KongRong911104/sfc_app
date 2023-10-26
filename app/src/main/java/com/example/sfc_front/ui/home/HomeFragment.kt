package com.example.sfc_front.ui.home

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.net.Uri
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.sfc_front.MainActivity
import com.example.sfc_front.R
import com.example.sfc_front.databinding.FragmentHomeBinding
import com.example.sfc_front.ui.AES.AES256
import com.example.sfc_front.ui.FDAES.FDAES
import java.io.File
import android.content.Context
import android.graphics.Color
import android.provider.MediaStore
import android.provider.OpenableColumns
import android.widget.Switch
import androidx.activity.result.ActivityResultLauncher
import java.io.FileWriter
import androidx.biometric.BiometricPrompt
import androidx.core.app.ActivityCompat
import androidx.core.content.FileProvider
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.FragmentActivity
import com.google.android.material.navigation.NavigationView
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.concurrent.Executors


import android.app.AlertDialog
import android.content.DialogInterface
import android.text.InputType


class HomeFragment : Fragment() {
    private val viewModel: HomeViewModel by viewModels()
    private var _binding: FragmentHomeBinding? = null
    private lateinit var progressObserver: Observer<Int>
    private val binding get() = _binding!!
    val aes256 = AES256("sixsquare1234567")
    val fdaes = FDAES("sixsquare1234567")

    private lateinit var takePictureLauncher: ActivityResultLauncher<Uri>
    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var promptInfo: BiometricPrompt.PromptInfo
    private var failAuthentication = 0
    private var FileName =""
    //    setSupportActionBar(binding.appBarMain.toolbar)
//    val drawerLayout: DrawerLayout = binding.drawerLayout
//    val navView: NavigationView = binding.navView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val ballView = root.findViewById<TextView>(R.id.ball_text)
        val progressBar: ProgressBar = root.findViewById(R.id.progressBar)
        val ball = root.findViewById<ProgressBar>(R.id.progressBar)
        val ballText = root.findViewById<TextView>(R.id.ball_text)
        progressObserver = Observer { progressInt ->
            ballView.text = "$progressInt%"
            progressBar.progress = progressInt
        }
        viewModel.progressInt.observe(viewLifecycleOwner, progressObserver)
        viewModel.startTask()
        val noteButton = root.findViewById<ImageButton>(R.id.note_button)
        noteButton.setOnClickListener {
            val intent = Intent(getActivity(), NoteActivity::class.java)
            startActivity(intent)
        }
        val takePictureButton = root.findViewById<ImageButton>(R.id.camera_button)
        // 设置点击事件，调用拍照方法
        takePictureButton.setOnClickListener {
            takeAPhoto()
        }
        val takeVideoButton = root.findViewById<ImageButton>(R.id.video_button)
        takeVideoButton.setOnClickListener {
            takeAVideo()
        }
        takePictureLauncher = registerForActivityResult(ActivityResultContracts.TakePicture()) { isTaken ->
            if (isTaken) {
                Toast.makeText(getActivity(), "Photo has been taken and saved", Toast.LENGTH_SHORT).show()
                val context = requireContext()
                val inputFile  = File(context.getExternalFilesDir(null), FileName)

                val executor = Executors.newSingleThreadExecutor()

                executor.execute {
                    try {
                        requireActivity().runOnUiThread {
                            ballText.setTextColor(Color.parseColor("#FFFFFFFF"))
                            ball.progressDrawable = resources.getDrawable(R.drawable.ball, null)
                        }
//                        val switch : Switch = findViewById<Switch>(R.id.switchButton)

//                        if (switch.isChecked){
//                            val outputFile=File(getExternalFilesDir(null),"FDAES_Encrypted_$FileName")
//                            fdaes.FileEncryption_CBC(inputFile,outputFile)
//                        }
//                        else{
                        val outputFile=File(context.getExternalFilesDir(null),"AES_Encrypted_$FileName")
                        // 在線程池中執行加密操作
                        aes256.encryptFile(inputFile, outputFile)
//                        }


                        // 刪除inputFile
                        if (inputFile.exists()) {
                            inputFile.delete()
                        }
                    } finally {
                        requireActivity().runOnUiThread {
                            ballText.setTextColor(Color.parseColor("#00FFFFFF"))
                            ball.progressDrawable = resources.getDrawable(R.drawable.logo, null)
                        }
                        executor.shutdown()
                    }
                }
            } else {
                Toast.makeText(getActivity(), "Unable to take a photo", Toast.LENGTH_SHORT).show()
            }
        }
        @SuppressLint("UseSwitchCompatOrMaterialCode")
        fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
            super.onActivityResult(requestCode, resultCode, data)
            val ball = root.findViewById<ProgressBar>(R.id.progressBar)
            val ballText = root.findViewById<TextView>(R.id.ball_text)
            if (requestCode == MainActivity.REQUEST_VIDEO_CAPTURE && resultCode == Activity.RESULT_OK) {
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
        return root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.progressInt.removeObserver(progressObserver)
        _binding = null
    }



    private fun takeAPhoto() {
        // 检查相机权限
        if (getActivity()?.let {
                ContextCompat.checkSelfPermission(
                    it,
                    Manifest.permission.CAMERA
                )
            } != PackageManager.PERMISSION_GRANTED
        ) {
            getActivity()?.let {
                ActivityCompat.requestPermissions(
                    it, arrayOf(Manifest.permission.CAMERA),
                    MainActivity.REQUEST_CAMERA_PERMISSION
                )
            }
            return
        }
        val context = requireContext()
        // 创建保存照片的目录
        val photoDirectory = File(context.getExternalFilesDir(null), "")
        // 创建文件名
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val photoFileName = "IMG_$timeStamp.png"
        //等關閉後執行加密用
        FileName = photoFileName
        // 创建文件
        val photoFile = File(photoDirectory, photoFileName)

        val photoUri =
            getActivity()?.let { FileProvider.getUriForFile(it, "com.example.sfc_front.fileprovider", photoFile) }


        // 启动拍照 Intent
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
        // 启动拍照
        takePictureLauncher.launch(photoUri)

    }
    private fun takeAVideo() {
        // 检查相机和录制视频的权限
        if (ContextCompat.checkSelfPermission(requireActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED ||
            ContextCompat.checkSelfPermission(requireActivity(), Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO),
                MainActivity.REQUEST_VIDEO_PERMISSION
            )
            return
        }
        val context = requireContext()
        // 创建保存视频的目录
        val videoDirectory = File(context.getExternalFilesDir(null), "")
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

        val videoUri = FileProvider.getUriForFile(requireActivity(), "com.example.sfc_front.fileprovider", videoFile)

        // 启动视频录制 Intent
        val takeVideoIntent = Intent(MediaStore.ACTION_VIDEO_CAPTURE)
        takeVideoIntent.putExtra(MediaStore.EXTRA_OUTPUT, videoUri)

        // 启动录制视频
        startActivityForResult(takeVideoIntent, MainActivity.REQUEST_VIDEO_CAPTURE)
    }

    companion object {
        const val PICK_PDF_FILE = 2
        private const val REQUEST_CAMERA_PERMISSION = 101
        private const val REQUEST_VIDEO_PERMISSION = 102
        private const val REQUEST_VIDEO_CAPTURE = 103
    }
}

