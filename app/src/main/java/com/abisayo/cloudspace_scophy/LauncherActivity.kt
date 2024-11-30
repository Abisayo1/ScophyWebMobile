package com.abisayo.cloudspace_scophy

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.abisayo.cloudspace_scophy.databinding.ActivityLauncherBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

class LauncherActivity : AppCompatActivity() {
    lateinit var binding: ActivityLauncherBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Thread.sleep(3000)
        installSplashScreen()
        binding = ActivityLauncherBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button2.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding.button.setOnClickListener {
            val auth = Firebase.auth
            if (auth.currentUser != null) {
                startActivity(Intent(this, MemberDashboardActivity::class.java))
            } else {
                val intent = Intent(this, SignInActivity::class.java)
                startActivity(intent)
            }


        }


    }
}