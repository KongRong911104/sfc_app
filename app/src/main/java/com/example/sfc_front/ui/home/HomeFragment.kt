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
        progressObserver = Observer { progressInt ->
            ballView.text = "$progressInt%"
            progressBar.progress = progressInt
        }
        viewModel.progressInt.observe(viewLifecycleOwner, progressObserver)
        viewModel.startTask()


        return root
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

