package com.grupoa.sparkyoutofbounds.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.grupoa.sparkyoutofbounds.databinding.ActivityExtraIngredientBinding

class ExtraIngredientActivity : AppCompatActivity() {

    private lateinit var binding: ActivityExtraIngredientBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExtraIngredientBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}