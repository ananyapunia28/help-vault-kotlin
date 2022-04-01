package com.dsckiet.helpvault.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.dsckiet.helpvault.R
import com.dsckiet.helpvault.databinding.ActivitySignUpBinding
import com.dsckiet.helpvault.util.SessionManager
import com.dsckiet.helpvault.viewModel.SignupViewModel
import java.util.regex.Pattern


class SignUpActivity : AppCompatActivity() {
    private lateinit var binding:ActivitySignUpBinding
    private var validate: Boolean = true
    lateinit var sessionManager: SessionManager
    private val viewModel: SignupViewModel by viewModels()
    private val PASSWORD_PATTERN = Pattern.compile(
        "^" + "(?=.*[0-9])" + "(?=.*[A-Z])" + ".{8,20}" + "$"
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_sign_up)
        sessionManager = SessionManager(this)
        binding.btnSignUp.setOnClickListener{
            val isValidate = checkValidation(
                binding.usernameInput.text.toString(),
                binding.usernamePassword.text.toString()
            )
            if (isValidate) {
                viewModel.signup(
                    binding.usernameInput.text.toString(),
                    binding.usernamePassword.text.toString()
                ).observe(this , Observer {
                    if(it != null) {
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                })
            }
//            val intent = Intent(this, MainActivity::class.java)
//            startActivity(intent)
//            finish()
        }
        binding.loginText.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
    private fun checkValidation(email: String, pass: String): Boolean {

        if (email.isEmpty()) {
            binding.usernameInput.error = "Email is Mandatory"
            binding.usernameInput.requestFocus()
            validate = false
        } else {
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                validate = false
                binding.usernameInput.error = "Please enter valid email"
                binding.usernameInput.requestFocus()
            } else {
                if (pass.isEmpty()) {
                    validate = false
                    binding.usernamePassword.setError("Password is Mandatory", null)
                    binding.usernamePassword.requestFocus()
                }
            }
        }

        return validate
    }
}