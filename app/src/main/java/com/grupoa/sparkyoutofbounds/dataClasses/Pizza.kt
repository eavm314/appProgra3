package com.grupoa.sparkyoutofbounds.dataClasses

import com.grupoa.sparkyoutofbounds.R

data class Pizza(
    val title: String = "",
    val image: Int = R.drawable.pizza_4estaciones,
    val info: String = "",
    var selected : String = "000000000000000000",
    val ingredients: List<Ingredient> = listOf(
        Ingredient("Aceituna", 10.0),
        Ingredient("Albondigas", 20.0),
        Ingredient("Brócoli", 15.0),
        Ingredient("Carne", 15.0),
        Ingredient("Cebolla", 15.0),
        Ingredient("Cereza", 15.0),
        Ingredient("Champiñones", 15.0),
        Ingredient("Chorizo", 15.0),
        Ingredient("Jamón", 15.0),
        Ingredient("Queso", 15.0),
        Ingredient("Locoto", 15.0),
        Ingredient("Morrón", 15.0),
        Ingredient("Pepperoni", 15.0),
        Ingredient("Piña", 15.0),
        Ingredient("Ricotta", 15.0),
        Ingredient("Salame", 15.0),
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



