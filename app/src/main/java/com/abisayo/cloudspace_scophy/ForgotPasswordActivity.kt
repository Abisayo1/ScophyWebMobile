package com.abisayo.cloudspace_scophy

import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.abisayo.cloudspace_scophy.databinding.ActivityForgotPasswordBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class ForgotPasswordActivity : BaseActivity() {
    private var binding: ActivityForgotPasswordBinding? = null
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgotPasswordBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        auth = Firebase.auth

        binding?.btnForgotPasswordSubmit?.setOnClickListener {
            resetPassword()
        }

    }

    private fun resetPassword()
    {
        val email = binding?.etForgotPasswordEmail?.text.toString()
        if (validateEmail(email))
        {
            showProgressBar()
            auth.sendPasswordResetEmail(email).addOnCompleteListener(this){task->
                if (task.isSuccessful)
                {
                    binding?.tilEmailForgetPassword?.visibility = View.GONE
                    binding?.tvSubmitMsg?.visibility = View.VISIBLE
                    binding?.btnForgotPasswordSubmit?.visibility = View.GONE
                    hideProgressBar()
                }
                else
                {
                    hideProgressBar()
                    showToast(this,"Reset password failed, try again latter")
                }
            }
        }
    }


    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    private fun validateEmail(email:String):Boolean
    {
        return if (TextUtils.isEmpty(email) && !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding?.tilEmailForgetPassword?.error = "Enter valid email address"
            false
        } else {
            binding?.tilEmailForgetPassword?.error = null
            true
        }
    }
}