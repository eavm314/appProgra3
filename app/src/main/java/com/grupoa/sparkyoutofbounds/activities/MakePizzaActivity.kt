package com.grupoa.sparkyoutofbounds.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.forEach
import androidx.recyclerview.widget.LinearLayoutManager
import com.grupoa.sparkyoutofbounds.activities.MenuActivity.Companion.PIZZA
import com.grupoa.sparkyoutofbounds.adapter.IngredientSelectAdapter
import com.grupoa.sparkyoutofbounds.dataClasses.Pizza
import com.grupoa.sparkyoutofbounds.databinding.ActivityMakePizzaBinding

class MakePizzaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMakePizzaBinding
    private lateinit var pizza: Pizza

    private val ingredientSelectAdapterLeft by lazy { IngredientSelectAdapter() }
    private val ingredientSelectAdapterRight by lazy { IngredientSelectAdapter() }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMakePizzaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setListeners()

        pizza = Pizza()
        setIngredientSelect()
    }

    fun setListeners() {
        binding.continueButton.setOnClickListener {
            if (pizza.ingredients.none { it.isSelected }) {
                Toast.makeText(this, "Seleccione al menos un ingrediente", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }

            val intent = Intent(this, ExtraIngredientActivity::class.java)
            intent.putExtra(PIZZA, pizza)
            startActivity(intent)

        }

        binding.radioButtons.setOnCheckedChangeListener { _, id ->
            binding.preciosTamanios.forEach { it.visibility = INVISIBLE }
            when (id) {
                binding.pequenia.id -> {
                    pizza.size = 0.75
                    binding.p1.visibility = VISIBLE
                }
                binding.regular.id -> {
                    pizza.size = 1.0
                    binding.p2.visibility = VISIBLE
                }
                binding.grande.id -> {
                    pizza.size = 1.5
                    binding.p3.visibility = VISIBLE
                }
                binding.interminable.id -> {
                    pizza.size = 2.0
                    binding.p4.visibility = VISIBLE
                }
            }

            val price = pizza.getPartialPrice()
            val newText = "${String.format("%.2f",price)} Bs"
            binding.precioP.text = newText
            Log.d("pizza", pizza.toString())
        }

    }

    fun setIngredientSelect() {
        ingredientSelectAdapterLeft.setTextViews(binding.precioR, binding.precioP)
        ingredientSelectAdapterRight.setTextViews(binding.precioR, binding.precioP)

        ingredientSelectAdapterLeft.addIngredientSelects(pizza,true)
        binding.ingredientesLeft.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = ingredientSelectAdapterLeft
        }

        ingredientSelectAdapterRight.addIngredientSelects(pizza,false)
        binding.ingredientesRight.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = ingredientSelectAdapterRight
        }
    }
}