package com.grupoa.sparkyoutofbounds.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.grupoa.sparkyoutofbounds.databinding.ItemPizzaCardBinding

class PizzaCardAdapter : RecyclerView.Adapter<PizzaCardAdapter.PizzaCardViewHolder>() {

        private val pizzaCard = mutableListOf<String>()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):PizzaCardViewHolder =
            PizzaCardViewHolder(
                ItemPizzaCardBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )

        override fun onBindViewHolder(holder: PizzaCardViewHolder, position: Int){
            holder.binding(pizzaCard[position])
        }

        override fun getItemCount(): Int = pizzaCard.size

        inner class PizzaCardViewHolder(private val binding: ItemPizzaCardBinding):
            RecyclerView.ViewHolder(binding.root){
            fun binding(data: String){
                //binding..text = data
            }
        }

        fun addPresentationCards(list: List<String>){
            pizzaCard.clear()
            pizzaCard.addAll(list)
        }

}
