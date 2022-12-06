package com.grupoa.sparkyoutofbounds.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.grupoa.sparkyoutofbounds.R
import com.grupoa.sparkyoutofbounds.adapter.PizzaCardAdapter
import com.grupoa.sparkyoutofbounds.databinding.ActivityMenuBinding

class MenuActivity : AppCompatActivity() {
    lateinit var binding: ActivityMenuBinding

    private val pizzaCardAdapter by lazy { PizzaCardAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setRecyclerView()
    }

    fun setRecyclerView(){

        val mutablelist = mutableListOf<String>()
        mutablelist.add("A")
        mutablelist.add("B")
        mutablelist.add("C")
        mutablelist.add("D")
        mutablelist.add("E")
        mutablelist.add("F")
        mutablelist.add("G")
        mutablelist.add("H")

        pizzaCardAdapter.addPresentationCards(mutablelist)

        binding.recyclerPizzaCard.apply {
            layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = pizzaCardAdapter
        }
    }
}