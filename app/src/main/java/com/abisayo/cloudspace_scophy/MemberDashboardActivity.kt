package com.abisayo.cloudspace_scophy

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.abisayo.cloudspace_scophy.databinding.ActivityMemberDashboardBinding

class MemberDashboardActivity : AppCompatActivity() {
    lateinit var binding : ActivityMemberDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMemberDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}