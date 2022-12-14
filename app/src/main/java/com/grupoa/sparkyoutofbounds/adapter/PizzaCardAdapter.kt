package com.grupoa.sparkyoutofbounds.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.grupoa.sparkyoutofbounds.activities.MakePizzaActivity
import com.grupoa.sparkyoutofbounds.activities.MenuActivity.Companion.PIZZA
import com.grupoa.sparkyoutofbounds.dataClasses.Pizza
import com.grupoa.sparkyoutofbounds.databinding.ItemPizzaCardBinding

class PizzaCardAdapter(var context: Context) : RecyclerView.Adapter<PizzaCardAdapter.PizzaCardViewHolder>() {

        private val pizzaCard = mutableListOf<Pizza>()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):PizzaCardViewHolder =
            PizzaCardViewHolder(
                ItemPizzaCardBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )

        override fun onBindViewHolder(holder: PizzaCardViewHolder, position: Int){
            holder.setData(pizzaCard[position])
        }

        override fun getItemCount(): Int = pizzaCard.size
        inner class PizzaCardViewHolder(private var binding: ItemPizzaCardBinding):
            RecyclerView.ViewHolder(binding.root){

            fun setData(pizza: Pizza){
                binding.textTitle.text = pizza.title
                binding.textInfo.text = pizza.info
                binding.imgPizza.setImageResource(pizza.image)
                binding.imgMenu.setOnClickListener{
                    if (binding.contText.visibility == View.GONE) {
                       // TransitionManager.beginDelayedTransition(binding.contText, Slide(Gravity.TOP))
                        binding.contText.visibility = View.VISIBLE
                    } else {
                       // TransitionManager.beginDelayedTransition(binding.contText, AutoTransition())
                        binding.contText.visibility = View.GONE
                    }
                }

                val str = pizza.selected
                for( i in 0 .. 17 ){
                    pizza.ingredients[i].isSelected = (str[i] == '1')
                }

                binding.btnGoToMakePizza.setOnClickListener{
                    val intent = Intent(context, MakePizzaActivity::class.java)
                    intent.putExtra(PIZZA,pizzaCard[adapterPosition])
                    context.startActivity(intent)
                }


            }
        }

        fun addPresentationCards(list: List<Pizza>){
            pizzaCard.clear()
            pizzaCard.addAll(list)
        }

}


