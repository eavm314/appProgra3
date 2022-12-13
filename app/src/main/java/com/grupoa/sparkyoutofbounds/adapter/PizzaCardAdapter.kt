package com.grupoa.sparkyoutofbounds.adapter

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.grupoa.sparkyoutofbounds.dataClasses.PizzaInfo
import com.grupoa.sparkyoutofbounds.databinding.ItemPizzaCardBinding

class PizzaCardAdapter : RecyclerView.Adapter<PizzaCardAdapter.PizzaCardViewHolder>() {

        private val pizzaCard = mutableListOf<PizzaInfo>()

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
        inner class PizzaCardViewHolder(private var binding: ItemPizzaCardBinding):
            RecyclerView.ViewHolder(binding.root){

            fun binding(data: PizzaInfo){
                binding.textTitle.text = data.title
                binding.textInfo.text = data.info
                binding.imgPizza.setImageResource(data.image)
                binding.imgMenu.setOnClickListener(){
                    if (binding.contText.visibility == View.GONE) {
                       // TransitionManager.beginDelayedTransition(binding.contText, Slide(Gravity.TOP))
                        binding.contText.visibility = View.VISIBLE
                    } else {
                       // TransitionManager.beginDelayedTransition(binding.contText, AutoTransition())
                        binding.contText.visibility = View.GONE
                    }
                }
            }
        }

        fun addPresentationCards(list: List<PizzaInfo>){
            pizzaCard.clear()
            pizzaCard.addAll(list)

        }

}


