package com.abisayo.cloudspace_scophy

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.abisayo.cloudspace_scophy.databinding.ActivitySignUpBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class SignUpActivity : BaseActivity() {
    private lateinit var auth:FirebaseAuth
    private var binding: ActivitySignUpBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        auth = Firebase.auth

        binding?.tvLoginPage?.setOnClickListener {
            startActivity(Intent(this, SignInActivity::class.java))
            finish()
        }

        binding?.btnSignUp?.setOnClickListener {
            registerUser()
        }

    }

    private fun validateForm(name: String, email: String, password: String): Boolean {
        return when {
            TextUtils.isEmpty(name)->{
                binding?.tilName?.error = "Enter name"
                false
            }
            TextUtils.isEmpty(email) && !Patterns.EMAIL_ADDRESS.matcher(email).matches()->{
                binding?.tilEmail?.error = "Enter valid email address"
                false
            }
            TextUtils.isEmpty(password)->{
                binding?.tilPassword?.error = "Enter password"
                false
            }
            else -> { true }
        }
    }

    private fun registerUser()
    {
        val name = binding?.etSinUpName?.text.toString()
        val email = binding?.etSinUpEmail?.text.toString()
        val password = binding?.etSinUpPassword?.text.toString()
        
        if (validateForm(name,email,password)) {
            showProgressBar()
            auth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener { task ->

                    if (task.isSuccessful) {
                        showToast(this, "User Id created Successfully")
                        hideProgressBar()
                        startActivity(Intent(this,LauncherActivity::class.java))
                        finish()
                    } else {
                        showToast(this, "User ID not created")
                        hideProgressBar()
                    }
                }
        }
    }
}