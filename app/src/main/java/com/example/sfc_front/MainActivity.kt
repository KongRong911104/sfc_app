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
import com.jcraft.jsch.ChannelSftp
import com.jcraft.jsch.JSch
import com.jcraft.jsch.Session
import java.io.File


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
        viewModel = ViewModelProvider(this)[HomeViewModel::class.java] // 替換成你的 ViewModel 類別
        // 設置觀察者
        progressObserver = Observer { progressInt ->
            ballText.text = "$progressInt%"
            ball.progress = progressInt
        }
        viewModel.progressInt.observe(this, progressObserver)
        viewModel.startTask()
        viewModel.progressInt.observe(this, progressObserver)

        switch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                // 启动云备份
                Toast.makeText(this, "Cloud Backup in Progress.", Toast.LENGTH_SHORT).show()

                val localFolderPath = "" // 替换为您要备份的本地文件夹路径
                val remoteFolderPath = "远程文件夹路径" // 替换为远程SFTP服务器上的文件夹路径

                // 使用SFTP连接进行备份
                val jsch = JSch()
                val session: Session = jsch.getSession("nmg", "172.24.8.170", 22)
                session.setPassword("e04su3su;6")
                session.connect()

                val channel: ChannelSftp = session.openChannel("sftp") as ChannelSftp
                channel.connect()

                try {
                    uploadDirectory(channel, localFolderPath, remoteFolderPath)
                    // 上传完成
                    runOnUiThread {
                        Toast.makeText(this, "Cloud Backup Completed.", Toast.LENGTH_SHORT).show()
                    }
                } catch (e: Exception) {
                    // 上传失败
                    e.printStackTrace()
                    runOnUiThread {
                        Toast.makeText(this, "Cloud Backup Failed.", Toast.LENGTH_SHORT).show()
                    }
                } finally {
                    channel.disconnect()
                    session.disconnect()
                }
            } else {
                // 关闭云备份
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
    private fun uploadDirectory(channel: ChannelSftp, localFolderPath: String, remoteFolderPath: String) {
        val localDirectory = File(localFolderPath)
        val remoteDirectory = remoteFolderPath

        if (!localDirectory.exists() || !localDirectory.isDirectory) {
            throw IllegalArgumentException("Local folder does not exist or is not a directory.")
        }

        // 上传文件夹内的文件和子文件夹
        for (file in localDirectory.listFiles()!!) {
            if (file.isFile) {
                channel.put(file.absolutePath, "$remoteDirectory/${file.name}")
            } else if (file.isDirectory) {
                val newRemoteFolder = "$remoteDirectory/${file.name}"
                channel.mkdir(newRemoteFolder)
                channel.cd(newRemoteFolder)
                uploadDirectory(channel, file.absolutePath, newRemoteFolder)
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
