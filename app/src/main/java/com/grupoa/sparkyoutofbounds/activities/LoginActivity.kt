package com.grupoa.sparkyoutofbounds.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.grupoa.sparkyoutofbounds.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnGoToNewUser.setOnClickListener {
            val intent = Intent(this, NewUserActivity::class.java)
            startActivity(intent)
        }
    }


}