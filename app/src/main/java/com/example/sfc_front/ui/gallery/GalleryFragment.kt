package com.example.sfc_front.ui.gallery
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.sfc_front.R
import com.example.sfc_front.databinding.FragmentSlideshowBinding

class GalleryFragment : Fragment() {
    private lateinit var slideshowViewModel: GalleryViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        slideshowViewModel =
            ViewModelProvider(this).get(GalleryViewModel::class.java)
        val root = inflater.inflate(R.layout.help, container, false)

        // 设置布局文件的内容
        slideshowViewModel.setLayoutContent(R.layout.help)
        return root
    }

}