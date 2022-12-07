package com.grupoa.sparkyoutofbounds.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.grupoa.sparkyoutofbounds.adapters.IngredientSelectAdapter
import com.grupoa.sparkyoutofbounds.dataClasses.Ingrediente
import com.grupoa.sparkyoutofbounds.databinding.ActivityMakePizzaBinding

class MakePizzaActivity : AppCompatActivity() {

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

        setIngredients()
    }

    fun setIngredients() {
        val listaIngredientes = mutableListOf(
            Ingrediente("Queso", 10, false),
            Ingrediente("Carne", 20, true),
            Ingrediente("Tocino", 15, true),
            Ingrediente("Tomate", 5, false)
        )

        ingredientSelectAdapter.addIngredientSelects(listaIngredientes)

        binding.ingredientesRV.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = ingredientSelectAdapter
        }
    }
}