package com.example.sfc_front.ui.home

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
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

class HomeFragment : Fragment() {
    private val viewModel: HomeViewModel by viewModels()
    private var _binding: FragmentHomeBinding? = null
    private lateinit var progressObserver: Observer<Int>
    private val binding get() = _binding!!
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
        noteButton.setOnClickListener{
            val intent = Intent(requireContext(), NoteActivity::class.java)
            startActivity(intent)
        }
        progressObserver = Observer { progressInt ->
            ballView.text = "$progressInt%"
            progressBar.progress = progressInt

            if (progressInt == 100) {
                viewModel.progressInt.removeObserver(progressObserver)
            }
        }

        viewModel.progressInt.observe(viewLifecycleOwner, progressObserver)

        val savedProgress = viewModel.getCurrentProgress()
        if (savedProgress < 100) {
            viewModel.startTask()
        } else {
            ballView.text = "100%"
            progressBar.progress = 100
        }

        return root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.progressInt.removeObserver(progressObserver)
        _binding = null
    }
    private fun startCamera() {
        val cameraProviderFuture = activity?.let { ProcessCameraProvider.getInstance(it) }

        activity?.let { ContextCompat.getMainExecutor(it) }?.let {
            cameraProviderFuture?.addListener({
                // Used to bind the lifecycle of cameras to the lifecycle owner
                val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()

                // Preview
                val preview = Preview.Builder()
                    .build()
                    .also {
                        it.setSurfaceProvider(_binding?.viewFinder?.surfaceProvider)
                    }

                // Select back camera as a default
                val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

                try {
                    // Unbind use cases before rebinding
                    cameraProvider.unbindAll()

                    // Bind use cases to camera
                    cameraProvider.bindToLifecycle(
                        this, cameraSelector, preview)

                } catch(exc: Exception) {
                    Log.e(MainActivity.TAG, "Use case binding failed", exc)
                }

            }, it)
        }
    }
    fun takeAPhoto(view: View) {
        requestPermissions()
    }

    private fun requestPermissions() {
        val activityResultLauncher =
            registerForActivityResult(
                ActivityResultContracts.RequestMultiplePermissions())
            { permissions ->
                // Handle Permission granted/rejected
                var permissionGranted = true
                permissions.entries.forEach {
                    if (it.key in MainActivity.REQUIRED_PERMISSIONS && !it.value)
                        permissionGranted = false
                }
                if (!permissionGranted) {
                    Toast.makeText(
                        requireContext(),
                        "Permission request denied",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    startCamera()
                }
            }

        // 在這裡啟動權限請求
        val permissionsToRequest = MainActivity.REQUIRED_PERMISSIONS.filter {
            ContextCompat.checkSelfPermission(requireContext(), it) != PackageManager.PERMISSION_GRANTED
        }.toTypedArray()

        if (permissionsToRequest.isNotEmpty()) {
            activityResultLauncher.launch(permissionsToRequest)
        } else {
            // 所有權限已經被授予
            startCamera()
        }
    }

}
