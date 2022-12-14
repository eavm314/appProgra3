package com.grupoa.sparkyoutofbounds.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.grupoa.sparkyoutofbounds.R
import com.grupoa.sparkyoutofbounds.adapter.PizzaCardAdapter
import com.grupoa.sparkyoutofbounds.dataClasses.Pizza
import com.grupoa.sparkyoutofbounds.databinding.ActivityMenuBinding

class MenuActivity : AppCompatActivity() {
    lateinit var binding: ActivityMenuBinding


    companion object {
        const val PIZZA: String = "enviar_pizza"
    }
    private val pizzaCardAdapter by lazy { PizzaCardAdapter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setRecyclerView()

        binding.btnCreatePizza.setOnClickListener{
            val intent = Intent(this, MakePizzaActivity::class.java)
            intent.putExtra(PIZZA, Pizza())
            startActivity(intent)
        }

    }

    fun setRecyclerView(){

        val pizzaList = listOf<Pizza>(
            Pizza(getString(R.string.title_pizza_hawaiana),R.drawable.pizza_hawaiana,getString(R.string.info_pizza_hawaiana), "000001001100010000"),
            Pizza(getString(R.string.title_pizza_4estaciones),R.drawable.pizza_4estaciones,getString(R.string.info_pizza_4estaciones), "100000101100100000"),
            Pizza(getString(R.string.title_pizza_carnivora),R.drawable.pizza_carnivora,getString(R.string.info_pizza_carnivora), "000000010100100110"),
            Pizza(getString(R.string.title_pizza_criolla),R.drawable.pizza_criolla,getString(R.string.info_pizza_criolla),"100110010110000001"),
            Pizza(getString(R.string.title_pizza_jamon),R.drawable.pizza_jamon,getString(R.string.info_pizza_jamon), "000000001100000000"),
            Pizza(getString(R.string.title_pizza_new_york),R.drawable.pizza_new_york,getString(R.string.info_pizza_new_york), "010010110100100000"),
            Pizza(getString(R.string.title_pizza_peperoni),R.drawable.pizza_peperoni,getString(R.string.info_pizza_peperoni), "000000000100100000"),
            Pizza(getString(R.string.title_pizza_queso),R.drawable.pizza_queso,getString(R.string.info_pizza_queso), "000000000100000000"),
            Pizza(getString(R.string.title_pizza_ricotta),R.drawable.pizza_ricotta,getString(R.string.info_pizza_ricotta), "000000000100001000"),
            Pizza(getString(R.string.title_pizza_vegetariana),R.drawable.pizza_vegetariana,getString(R.string.info_pizza_vegetariana), "101010000101000001")
        )


        pizzaCardAdapter.addPresentationCards(pizzaList)

        binding.recyclerPizzaCard.apply {
            layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = pizzaCardAdapter
        }
    }
}