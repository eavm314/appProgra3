package com.grupoa.sparkyoutofbounds.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.grupoa.sparkyoutofbounds.activities.MakePizzaActivity.Companion.PIZZA
import com.grupoa.sparkyoutofbounds.dataClasses.Pizza
import com.grupoa.sparkyoutofbounds.databinding.ActivityExtraIngredientBinding

class ExtraIngredientActivity : AppCompatActivity() {

    private lateinit var binding: ActivityExtraIngredientBinding
    private lateinit var pizza: Pizza

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExtraIngredientBinding.inflate(layoutInflater)
        setContentView(binding.root)

        pizza = intent.getSerializableExtra(PIZZA) as Pizza
        Log.d("pizza en otra pantalla", pizza.toString())
    }
}