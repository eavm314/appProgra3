package com.grupoa.sparkyoutofbounds.adapter

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

        fun setData(ingredient: Ingredient) {
            binding.ingrediente.isChecked = ingredient.isSelected
            binding.ingrediente.text = ingredient.name
            val text = "${String.format("%.2f", ingredient.price)} Bs"
            binding.precioI.text = text

            binding.ingrediente.setOnClickListener {
                val isChecked = binding.ingrediente.isChecked
                ingredientSelectList[adapterPosition].isSelected = isChecked

                var newText: String

                val price1 = pizza.getRegularPrice()
                newText = "${String.format("%.2f",price1)} Bs"
                priceR.text = newText

                val price2 = pizza.getPartialPrice()
                newText = "${String.format("%.2f",price2)} Bs"
                priceP.text = newText

//                for (ing in ingredientSelectList) {
//                    Log.d("Ingredient: ", ing.toString())
//                }
            }
        }
    }

    fun addIngredientSelects(pizza: Pizza, left: Boolean) {
        this.pizza = pizza
        val listSize = pizza.ingredients.size
        this.ingredientSelectList =
            if (left) pizza.ingredients.subList(0, (listSize + 1) / 2)
            else pizza.ingredients.subList((listSize + 1) / 2, listSize)
    }

    fun setTextViews(priceR: TextView, priceP: TextView) {
        this.priceR = priceR
        this.priceP = priceP
    }
}