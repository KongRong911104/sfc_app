package com.example.sfc_front.ui.slideshow

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

class SlideshowFragment : Fragment() {

    private var _binding: FragmentSlideshowBinding? = null
    private val binding get() = _binding!!

    private lateinit var slideshowViewModel: SlideshowViewModel
    private lateinit var idEditText: EditText // 输入ID为id_text的字段
    private lateinit var productKeyEditText: EditText // 输入ID为product_key_text的字段
    private lateinit var pwButton: Button
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        slideshowViewModel =
            ViewModelProvider(this).get(SlideshowViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_slideshow, container, false)

        // 设置布局文件的内容
        slideshowViewModel.setLayoutContent(R.layout.fragment_slideshow)

        // 获取输入字段

        pwButton = root.findViewById(R.id.pw_button)

        // 现在你可以使用 idEditText 和 productKeyEditText 来访问这两个输入字段
        pwButton.setOnClickListener {
            idEditText = root.findViewById(R.id.id_text)
            productKeyEditText = root.findViewById(R.id.product_key_text)
            val idText = idEditText.text.toString()
            val pk = productKeyEditText.text.toString()
            if (idText.isNotEmpty() && pk.isNotEmpty()) {
                Toast.makeText(
                    requireContext(),
                    "Retrieve your password via email",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(requireContext(), "Value can't be null", Toast.LENGTH_SHORT).show()
            }
        }
        return root
    }
//    pwButton.setOnClickListener {
//        val idText = idEditText.text.toString()
//        val productKeyText = productKeyEditText.text.toString()
//
//        if (idText.isEmpty() || productKeyText.isEmpty()) {
//            // 如果任一输入字段为空，显示 "Value can't be null" 的 Toast 消息
//            Toast.makeText(requireContext(), "Value can't be null", Toast.LENGTH_SHORT).show()
//        } else {
//            // 如果都不为空，显示 "Retrieve your password via email" 的 Toast 消息
//            Toast.makeText(requireContext(), "Retrieve your password via email", Toast.LENGTH_SHORT).show()
//            // 在这里添加发送密码到邮箱的逻辑
//        }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}




