package com.example.sfc_front
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.sfc_front.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView
import kotlin.system.exitProcess
import android.widget.ProgressBar
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.sfc_front.ui.home.HomeViewModel


class MainActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: HomeViewModel
    private lateinit var progressObserver: Observer<Int>
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val ball = findViewById<ProgressBar>(R.id.progressBar)
        val ballText = findViewById<TextView>(R.id.ball_text)
        setSupportActionBar(binding.appBarMain.toolbar)
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        val switch:Switch = findViewById(R.id.switchButton)
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home,R.id.nav_slideshow,R.id.nav_gallery
            ), drawerLayout
        )
        // 初始化 ViewModel
        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        // 设置观察者
        progressObserver = Observer { progressInt ->
            ballText.text = "$progressInt%"
            ball.progress = progressInt
        }

        // 首先观察 progressInt
        viewModel.progressInt.observe(this, progressObserver)

        // 启动任务
        viewModel.startTask()

        switch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked){
                Toast.makeText(this, "Cloud Backup in Progress.", Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(this, "Cloud Backup has been turned off", Toast.LENGTH_SHORT).show()
            }
        }
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        val goBack = findViewById<TextView>(R.id.logout)
        goBack.setOnClickListener {
            moveTaskToBack(true);
            exitProcess(-1)
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
    companion object {
        const val REQUEST_CAMERA_PERMISSION = 101
        const val REQUEST_VIDEO_PERMISSION = 102
        const val REQUEST_VIDEO_CAPTURE = 103
    }
    override fun onDestroy() {
        super.onDestroy()
        // 移除觀察者，以避免內存洩漏
        viewModel.progressInt.removeObserver(progressObserver)
    }
}
