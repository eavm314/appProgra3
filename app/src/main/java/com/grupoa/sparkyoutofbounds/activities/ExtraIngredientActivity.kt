package com.grupoa.sparkyoutofbounds.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.grupoa.sparkyoutofbounds.activities.MenuActivity.Companion.PIZZA
import com.grupoa.sparkyoutofbounds.adapter.IngredientModifierAdapter
import com.grupoa.sparkyoutofbounds.dataClasses.Pizza
import com.grupoa.sparkyoutofbounds.databinding.ActivityExtraIngredientBinding

class ExtraIngredientActivity : AppCompatActivity() {

    private lateinit var binding: ActivityExtraIngredientBinding
    private lateinit var pizza: Pizza

    private val ingredientModifierAdapter by lazy { IngredientModifierAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExtraIngredientBinding.inflate(layoutInflater)
        pizza = intent.getSerializableExtra(PIZZA) as Pizza
        setContentView(binding.root)
        setViews()
        setListeners()
        setIngredientModifierRecyclerView()
    }

    private fun setListeners() {
        binding.continueButton.setOnClickListener {
            val intent = Intent(this, OrderConfirmActivity::class.java)
            intent.putExtra(PIZZA, pizza)
            startActivity(intent)
        }
    }

    private fun setViews(){
        val priceP = pizza.getPartialPrice()
        val newText = "${String.format("%.2f",priceP)} Bs"
        binding.priceP.text = newText
        binding.priceT.text = newText
        binding.image.setImageResource(pizza.image)
    }

    private fun setIngredientModifierRecyclerView() {
        ingredientModifierAdapter.addIngredientModifier(pizza)
        ingredientModifierAdapter.setTextView(binding.priceT)

        binding.ingredientsAmount.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = ingredientModifierAdapter
        }
    }
}