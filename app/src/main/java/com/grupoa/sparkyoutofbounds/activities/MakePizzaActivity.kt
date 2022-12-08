package com.grupoa.sparkyoutofbounds.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.grupoa.sparkyoutofbounds.adapters.IngredientSelectAdapter
import com.grupoa.sparkyoutofbounds.dataClasses.Pizza
import com.grupoa.sparkyoutofbounds.databinding.ActivityMakePizzaBinding

class MakePizzaActivity : AppCompatActivity() {

    val pizza: Pizza = Pizza()

    private lateinit var binding: ActivityMakePizzaBinding

    private val ingredientSelectAdapter by lazy { IngredientSelectAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMakePizzaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.continueButton.setOnClickListener{
            val intent = Intent(this,ExtraIngredientActivity::class.java)
            startActivity(intent)
        }

        binding.radioButtons.setOnCheckedChangeListener { _, id ->
            val size = when (id){
                binding.pequenia.id -> 0.5
                binding.regular.id -> 1.0
                binding.grande.id -> 1.5
                binding.interminable.id -> 2.0
                else -> 1.0
            }
            pizza.size = size

            Log.d("pizza", pizza.toString())
        }
        setIngredientSelect()
    }

    fun setIngredientSelect() {
        val listaIngredientes = pizza.ingredients

        ingredientSelectAdapter.addIngredientSelects(listaIngredientes)

        binding.ingredientesRV.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = ingredientSelectAdapter
        }
    }
}