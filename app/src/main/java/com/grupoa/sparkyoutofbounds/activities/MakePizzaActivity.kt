package com.grupoa.sparkyoutofbounds.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.grupoa.sparkyoutofbounds.databinding.ActivityMakePizzaBinding

class MakePizzaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMakePizzaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMakePizzaBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}