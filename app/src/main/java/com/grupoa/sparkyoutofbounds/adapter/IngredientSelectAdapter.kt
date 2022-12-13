package com.grupoa.sparkyoutofbounds.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.grupoa.sparkyoutofbounds.dataClasses.Ingredient
import com.grupoa.sparkyoutofbounds.dataClasses.Pizza
import com.grupoa.sparkyoutofbounds.databinding.ItemIngredientSelectBinding

class IngredientSelectAdapter :
    RecyclerView.Adapter<IngredientSelectAdapter.IngredientSelectViewHolder>() {
    private lateinit var pizza: Pizza
    private lateinit var ingredientSelectList: List<Ingredient>

    private lateinit var priceR: TextView
    private lateinit var priceP: TextView

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientSelectViewHolder =
        IngredientSelectViewHolder(
            ItemIngredientSelectBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: IngredientSelectViewHolder, position: Int) {
        holder.setData(ingredientSelectList[position])
    }

    override fun getItemCount(): Int = ingredientSelectList.size

    inner class IngredientSelectViewHolder(private val binding: ItemIngredientSelectBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.ingrediente.setOnClickListener {
                val isChecked = binding.ingrediente.isChecked
                ingredientSelectList[adapterPosition].isSelected = isChecked

                val price1 = pizza.getRegularPrice()
                priceR.text = "$price1 Bs"

                val price2 = pizza.getPartialPrice()
                priceP.text = "$price2 Bs"

                for (ing in ingredientSelectList) {
                    Log.d("Ingrediente: ", ing.toString())
                }
            }
        }

        fun setData(ingredient: Ingredient) {
            binding.ingrediente.isChecked = ingredient.isSelected
            binding.ingrediente.text = ingredient.name
            binding.precioI.text = "${ingredient.price} Bs"
        }
    }

    fun addIngredientSelects(pizza: Pizza) {
        this.pizza = pizza
        this.ingredientSelectList = pizza.ingredients
    }

    fun recieveTextViews(priceR:TextView, priceP:TextView){
        this.priceR = priceR
        this.priceP = priceP
    }
}