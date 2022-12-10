package com.grupoa.sparkyoutofbounds.dataClasses

data class Pizza(
    val ingredients: List<Ingredient> = listOf(
        Ingredient("Queso", 10.0),
        Ingredient("Carne", 20.0),
        Ingredient("Tocino", 15.0),
        Ingredient("Tomate", 5.0)
    ),
    var size: Double = 1.0
) : java.io.Serializable {
    fun getRegularPrice() = ingredients.filter { it.isSelected }.sumOf { it.price }
    fun getPartialPrice() = getRegularPrice() * size
    fun getTotalPrice() =
        ingredients.filter { it.isSelected }.sumOf { it.price * it.modifier } * size
}



