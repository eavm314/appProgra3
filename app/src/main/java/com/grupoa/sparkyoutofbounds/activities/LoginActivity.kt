package com.grupoa.sparkyoutofbounds.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.grupoa.sparkyoutofbounds.R
import com.grupoa.sparkyoutofbounds.databinding.ActivityLoginBinding
import com.grupoa.sparkyoutofbounds.databinding.ActivityMenuBinding

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}