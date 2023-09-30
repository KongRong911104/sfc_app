package com.example.sfc_front.ui.home
import android.net.Uri
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
        val fileProtectButton : ImageButton = root.findViewById(R.id.file_protection_button)
        fileProtectButton.setOnClickListener{
            protectFile()
        }
        noteButton.setOnClickListener{
            val intent = Intent(requireContext(), NoteActivity::class.java)
            startActivity(intent)
        }
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

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.progressInt.removeObserver(progressObserver)
        _binding = null
    }

    companion object {
        const val PICK_PDF_FILE = 2
    }


}

