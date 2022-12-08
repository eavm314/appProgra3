package com.grupoa.sparkyoutofbounds.adapter

import android.R
import android.os.Build
import android.transition.AutoTransition
import android.transition.Fade
import android.transition.Transition

import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
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

        inner class PizzaCardViewHolder(private var binding: ItemPizzaCardBinding):
            RecyclerView.ViewHolder(binding.root){
            fun binding(data: String){
                binding.textInfo.text = data

                binding.cardPizza.setOnClickListener(){
                    if (binding.contText.visibility == View.VISIBLE) {
                        // The transition of the hiddenView is carried out by the TransitionManager class.
                        // Here we use an object of the AutoTransition Class to create a default transition
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                            TransitionManager.beginDelayedTransition(binding.contText, Fade(Fade.MODE_OUT))
                        }
                        binding.contText.visibility = View.GONE
                    } else {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                            TransitionManager.beginDelayedTransition(binding.contText, Fade(Fade.MODE_IN))
                        }
                        binding.contText.visibility = View.VISIBLE
                    }
                }
            }
        }

        fun addPresentationCards(list: List<String>){
            pizzaCard.clear()
            pizzaCard.addAll(list)

        }

}
