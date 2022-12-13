package com.grupoa.sparkyoutofbounds.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.grupoa.sparkyoutofbounds.activities.MakePizzaActivity.Companion.PIZZA
import com.grupoa.sparkyoutofbounds.adapter.IngredientModifierAdapter
import com.grupoa.sparkyoutofbounds.dataClasses.Pizza
import com.grupoa.sparkyoutofbounds.databinding.ActivityExtraIngredientBinding

class ExtraIngredientActivity : AppCompatActivity() {

    private lateinit var binding: ActivityExtraIngredientBinding
    private lateinit var pizza: Pizza

    private val ingredientModifierAdapter by lazy { IngredientModifierAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExtraIngredientBinding.inflate(layoutInflater)
        pizza = intent.getSerializableExtra(PIZZA) as Pizza
        setContentView(binding.root)
        setViews()
        setListeners()

        Log.d("pizza en otra pantalla", pizza.toString())
        setIngredientSelect()
    }

    fun setListeners() {
        binding.continueButton.setOnClickListener {
            // TODO: Cambiar a la pantalla del mapa
//            val intent = Intent(this, ExtraIngredientActivity::class.java)
//            intent.putExtra(PIZZA, pizza)
//            startActivity(intent)
        }
    }

    fun setViews(){
        val newText: String
        val precioP = pizza.getPartialPrice()
        newText = "${String.format("%.2f",precioP)} Bs"
        binding.precioP.text = newText
        binding.precioT.text = newText
    }

    fun setIngredientSelect() {
        ingredientModifierAdapter.addIngredientModifier(pizza)
        ingredientModifierAdapter.setTextView(binding.precioT)

        binding.cantidadIngredientes.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = ingredientModifierAdapter
        }
    }
}