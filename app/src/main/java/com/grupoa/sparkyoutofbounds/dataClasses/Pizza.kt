package com.grupoa.sparkyoutofbounds.dataClasses

import com.grupoa.sparkyoutofbounds.R

data class Pizza(
    val title: String = "",
    val image: Int = R.drawable.pizza_4estaciones,
    val info: String = "",
    val ingredients: List<Ingredient> = listOf(
        Ingredient("Queso", 10.0, true),
        Ingredient("Carne", 20.0, true),
        Ingredient("Tocino", 15.0),
        Ingredient("Tocino", 15.0),
        Ingredient("Tocino", 15.0),
        Ingredient("Tocino", 15.0),
        Ingredient("Tocino", 15.0),
        Ingredient("Tocino", 15.0),
        Ingredient("Tocino", 15.0),
        Ingredient("Tocino", 15.0, true),
        Ingredient("Tocino", 15.0),
        Ingredient("Tocino", 15.0),
        Ingredient("Tocino", 15.0),
        Ingredient("Tocino", 15.0),
        Ingredient("Tocino", 15.0),
        Ingredient("Tocino", 15.0),
        Ingredient("Tocino", 15.0),
        Ingredient("Tocino", 15.0),
        Ingredient("Tocino", 15.0),
        Ingredient("Tocino", 15.0),
        Ingredient("Tocino", 15.0),
        Ingredient("Tocino", 15.0),
        Ingredient("Tocino", 15.0),
        Ingredient("Tocino", 15.0),
        Ingredient("Tocino", 15.0),
        Ingredient("Tomate", 5.0)
    ),
    var size: Double = 1.0
) : java.io.Serializable {
    fun getRegularPrice() = ingredients.filter { it.isSelected }.sumOf { it.price }
    fun getPartialPrice() = getRegularPrice() * size
    fun getTotalPrice() = getPartialPrice() +
        ingredients.sumOf { it.price * it.modifier }
}



