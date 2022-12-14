package com.grupoa.sparkyoutofbounds.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.grupoa.sparkyoutofbounds.dataClasses.Ingredient
import com.grupoa.sparkyoutofbounds.dataClasses.Pizza
import com.grupoa.sparkyoutofbounds.databinding.ItemIngredientModifierBinding

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

        fun setData(ingredient: Ingredient) {
            binding.ingrediente.text = ingredient.name

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

                var newText: String

                val priceCurrent = currentIngredient.price*currentIngredient.modifier
                newText = "${(if(priceCurrent>0) '+' else "")}${String.format("%.2f",priceCurrent)} Bs"
                binding.precio.text = newText

                val priceTotal = pizza.getTotalPrice()
                newText = "${String.format("%.2f",priceTotal)} Bs"
                priceT.text = newText

//                for (ing in ingredientModifierList) {
//                    Log.d("Ingredient: ", ing.toString())
//                }
            }

        }
    }

    fun addIngredientModifier(pizza: Pizza) {
        this.pizza = pizza
        this.ingredientModifierList = pizza.ingredients.filter { it.isSelected }
    }

    fun setTextView(priceT: TextView){
        this.priceT = priceT
    }
}