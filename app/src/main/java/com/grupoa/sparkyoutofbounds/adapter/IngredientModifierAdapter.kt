package com.grupoa.sparkyoutofbounds.adapter

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.grupoa.sparkyoutofbounds.R
import com.grupoa.sparkyoutofbounds.dataClasses.Ingredient
import com.grupoa.sparkyoutofbounds.dataClasses.Pizza
import com.grupoa.sparkyoutofbounds.databinding.ItemIngredientModifierBinding
import com.grupoa.sparkyoutofbounds.databinding.ItemIngredientSelectBinding

class IngredientModifierAdapter :
    RecyclerView.Adapter<IngredientModifierAdapter.IngredientModifierViewHolder>() {

    private lateinit var pizza: Pizza
    private lateinit var ingredientModifierList: List<Ingredient>

    private lateinit var priceT: TextView

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            IngredientModifierAdapter.IngredientModifierViewHolder = IngredientModifierViewHolder(
        ItemIngredientModifierBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: IngredientModifierViewHolder, position: Int) {
        holder.setData(pizza.ingredients[position])
    }

    override fun getItemCount(): Int = ingredientModifierList.size

    inner class IngredientModifierViewHolder(private val binding: ItemIngredientModifierBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.radioButtons.setOnCheckedChangeListener { _, id ->
                val currentIngredient = ingredientModifierList[adapterPosition]
                when (id) {
                    binding.poco.id -> {
                        currentIngredient.modifier = -0.2
                        binding.precio.setTextColor(Color.parseColor("#FF0000"))
                    }
                    binding.normal.id -> {
                        currentIngredient.modifier = 0.0
                        binding.precio.setTextColor(Color.parseColor("#000000"))
                    }
                    binding.extra.id -> {
                        currentIngredient.modifier = 0.4
                        binding.precio.setTextColor(Color.parseColor("#00FF00"))
                    }
                }

                val priceCurrent = currentIngredient.price*currentIngredient.modifier
                binding.precio.text = "$priceCurrent Bs"

                val priceTotal = pizza.getTotalPrice()
                priceT.text = "$priceTotal Bs"

                for (ing in ingredientModifierList) {
                    Log.d("Ingrediente: ", ing.toString())
                }

            }
        }

        fun setData(ingredient: Ingredient) {
            binding.ingrediente.text = ingredient.name
        }
    }

    fun addIngredientModifier(pizza: Pizza) {
        this.pizza = pizza
        this.ingredientModifierList = pizza.ingredients.filter { it.isSelected }
    }

    fun recieveTextView(priceT: TextView){
        this.priceT = priceT
    }
}