package com.grupoa.sparkyoutofbounds.activities

import android.content.Intent
import android.os.Bundle
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
        pizza = intent.getSerializableExtra(PIZZA) as Pizza
        setContentView(binding.root)
        setViews()
        setListeners()
        setIngredientSelectRecyclerView()
    }

    private fun setListeners() {
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
            binding.sizesPrice.forEach { it.visibility = INVISIBLE }
            when (id) {
                binding.small.id -> {
                    pizza.size = 0.75
                    binding.p1.visibility = VISIBLE
                }
                binding.regular.id -> {
                    pizza.size = 1.0
                    binding.p2.visibility = VISIBLE
                }
                binding.large.id -> {
                    pizza.size = 1.5
                    binding.p3.visibility = VISIBLE
                }
                binding.extraLarge.id -> {
                    pizza.size = 2.0
                    binding.p4.visibility = VISIBLE
                }
            }

            val price = pizza.getPartialPrice()
            val newText = "${String.format("%.2f",price)} Bs"
            binding.priceP.text = newText
        }
    }

    private fun setViews(){
        val priceR = pizza.getRegularPrice()
        val newText = "${String.format("%.2f",priceR)} Bs"
        binding.priceR.text = newText
        binding.priceP.text = newText
    }

    private fun setIngredientSelectRecyclerView() {
        ingredientSelectAdapterLeft.setTextViews(binding.priceR, binding.priceP)
        ingredientSelectAdapterRight.setTextViews(binding.priceR, binding.priceP)

        ingredientSelectAdapterLeft.addIngredientSelects(pizza,true)
        binding.ingredientsLeft.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = ingredientSelectAdapterLeft
        }

        ingredientSelectAdapterRight.addIngredientSelects(pizza,false)
        binding.ingredientsRight.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = ingredientSelectAdapterRight
        }
    }
}