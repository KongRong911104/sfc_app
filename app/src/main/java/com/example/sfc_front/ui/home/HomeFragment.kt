package com.example.sfc_front.ui.home
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
import android.provider.OpenableColumns
import android.widget.Switch
import java.io.FileWriter
import androidx.biometric.BiometricPrompt
import androidx.fragment.app.FragmentActivity


class HomeFragment : Fragment() {
    private val viewModel: HomeViewModel by viewModels()
    private var _binding: FragmentHomeBinding? = null
    private lateinit var progressObserver: Observer<Int>
    private val binding get() = _binding!!
    val aes256 = AES256("sixsquare1234567")
    val fdaes = FDAES("sixsquare1234567")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val ballView = root.findViewById<TextView>(R.id.ball_text)
        val progressBar: ProgressBar = root.findViewById(R.id.progressBar)
        val cameraButton : ImageButton = root.findViewById(R.id.camera_button)
        val noteButton : ImageButton = root.findViewById(R.id.note_button)
//        val fileProtectButton : ImageButton = root.findViewById(R.id.file_protection_button)
//        fileProtectButton.setOnClickListener{
//
//            protectFile()
//        }
//        noteButton.setOnClickListener{
//            val intent = Intent(requireContext(), NoteActivity::class.java)
//            startActivity(intent)
//        }
        progressObserver = Observer { progressInt ->
            ballView.text = "$progressInt%"
            progressBar.progress = progressInt
        }
        viewModel.progressInt.observe(viewLifecycleOwner, progressObserver)
        viewModel.startTask()


        return root
    }
    fun openFile(pickerInitialUri: Uri) {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
            addCategory(Intent.CATEGORY_OPENABLE)
            type = "*/*"

            // Optionally, specify a URI for the file that should appear in the
            // system file picker when it loads.
//            putExtra(DocumentsContract.EXTRA_INITIAL_URI, pickerInitialUri)
            addCategory(Intent.CATEGORY_OPENABLE)

        }

        startActivityForResult(intent,2)
    }


//    private fun protectFile() {
//        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
//            addCategory(Intent.CATEGORY_OPENABLE)
//            type = "*/*"
//
//            // Optionally, specify a URI for the file that should appear in the
//            // system file picker when it loads.
////            putExtra(DocumentsContract.EXTRA_INITIAL_URI, pickerInitialUri)
//
//        }
//
//        startActivityForResult(intent, 2)
//    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_PDF_FILE && resultCode == Activity.RESULT_OK) {
            // 处理选择的文件，您可以使用选定的文件进行进一步操作
            val selectedFileUri = data?.data
            val FileName = selectedFileUri?.let { getFileNameFromUri(it) }
            if (selectedFileUri != null) {
                try {
                    val contentResolver = requireContext().contentResolver
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
                        val outputFile = File(requireContext().getExternalFilesDir(null), "AES_Encrypted_$FileName")

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
        val cursor = requireContext().contentResolver.query(uri, null, null, null, null)
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


    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.progressInt.removeObserver(progressObserver)
        _binding = null
    }

    companion object {
        const val PICK_PDF_FILE = 2
    }


}

