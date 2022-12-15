package com.grupoa.sparkyoutofbounds.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.grupoa.sparkyoutofbounds.dataClasses.Pizza
import com.grupoa.sparkyoutofbounds.databinding.ActivityOrderConfirm2Binding

class OrderConfirmActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOrderConfirm2Binding
    private lateinit var pizza: Pizza

    companion object{
        val NOMBREFACTURA: String = "name"
        val NITFACTURA: String = "hola"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderConfirm2Binding.inflate(layoutInflater)
        pizza = intent.getSerializableExtra(MenuActivity.PIZZA) as Pizza

        setContentView(binding.root)
        setViews()
        setListeners()

        Log.d("pizza en otra pantalla", pizza.toString())
    }

    fun setListeners() {
        binding.confirmButton.setOnClickListener {
            val name = binding.editTextName.text.toString()
            val nit = binding.editTextNit.text.toString()
            if(!(name.isBlank() xor nit.isBlank())){
                val intent = Intent(this, OrderMapActivity::class.java)
                intent.putExtra(MenuActivity.PIZZA, pizza)
                intent.putExtra(NOMBREFACTURA,name)
                intent.putExtra(NITFACTURA,nit)
                startActivity(intent)
            }else{
                Toast.makeText(this, "Todos los datos deben ser llenados o dejados en blanco", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun setViews(){
        val newText: String
        val precio = pizza.getTotalPrice()
        newText = "${String.format("%.2f",precio)} Bs"
        binding.titlepizza.text=pizza.title.trim('-')
        binding.billPaidNumber.text = newText
        binding.imagepizza.setImageResource(pizza.image)
    }
}