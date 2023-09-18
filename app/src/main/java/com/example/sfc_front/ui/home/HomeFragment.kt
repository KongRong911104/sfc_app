package com.example.sfc_front.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.sfc_front.databinding.FragmentHomeBinding
import android.widget.TextView
import com.example.sfc_front.R
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
}
