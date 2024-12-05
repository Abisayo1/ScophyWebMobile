package com.abisayo.cloudspace_scophy

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.abisayo.cloudspace_scophy.databinding.ActivitySignInBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class SignInActivity : AppCompatActivity() {
    private var binding:ActivitySignInBinding? = null
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        Thread.sleep(3000)
        installSplashScreen()
        super.onCreate(savedInstanceState)

        auth = Firebase.auth
        if (auth.currentUser != null) {
            startActivity(Intent(this, LauncherActivity::class.java))
        }

        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.tvRegister?.setOnClickListener {
            startActivity(Intent(this,SignUpActivity::class.java))
            finish()
        }

        binding?.tvForgotPassword?.setOnClickListener {
            startActivity(Intent(this,ForgotPasswordActivity::class.java))
            finish()
        }

        binding?.btnSignIn?.setOnClickListener {
            sineInUser()
        }

    }

    private fun sineInUser()
    {
        val email = binding?.etSinInEmail?.text.toString()
        val password = binding?.etSinInPassword?.text.toString()
        if (validateForm(email, password))
        {

            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this){task->
                    if (task.isSuccessful)
                    {
                        startActivity(Intent(this,LauncherActivity::class.java))
                        finish()
                    }
                    else
                    {
                        binding?.btnSignIn?.text = "Login"
                        Toast.makeText(this,"Oops! Something went wrong", Toast.LENGTH_SHORT).show()
                    }

                }
        }
    }

    private fun validateForm(email:String,password:String):Boolean
    {
        return when {
            TextUtils.isEmpty(email) && !Patterns.EMAIL_ADDRESS.matcher(email).matches()->{
                binding?.tilEmail?.error = "Enter valid email address"
                false
            }
            TextUtils.isEmpty(password)->{
                binding?.tilPassword?.error = "Enter password"
                binding?.tilEmail?.error = null
                false
            }
            else -> {
                binding?.tilEmail?.error = null
                binding?.tilPassword?.error = null
                true }
        }
    }
}
