package com.grupoa.sparkyoutofbounds.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.grupoa.sparkyoutofbounds.dataClasses.Ingrediente
import com.grupoa.sparkyoutofbounds.databinding.ItemIngredientSelectBinding

class IngredientSelectAdapter :
    RecyclerView.Adapter<IngredientSelectAdapter.IngredientSelectViewHolder>() {
    private val ingredientSelectList = mutableListOf<Ingrediente>()

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

                for (ing in ingredientSelectList) {
                    Log.d("Ingrediente: ", ing.toString())
                }
            }
        }

            fun setData(data: Ingrediente) {
                binding.ingrediente.isChecked = data.isSelected
                binding.ingrediente.text = data.name
                binding.precioI.text = "${data.price} Bs"
            }
        }

        fun addIngredientSelects(list: List<Ingrediente>) {
            ingredientSelectList.clear()
            ingredientSelectList.addAll(list)
        }
    }