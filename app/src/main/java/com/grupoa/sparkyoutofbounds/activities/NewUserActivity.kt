package com.grupoa.sparkyoutofbounds.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.grupoa.sparkyoutofbounds.databinding.ActivityNewUserBinding

class NewUserActivity : AppCompatActivity() {
    lateinit var binding: ActivityNewUserBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}