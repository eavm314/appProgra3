package com.grupoa.sparkyoutofbounds.dataClasses

data class Ingredient(
    val name: String,
    val price: Double,
    var isSelected: Boolean = false,
    var modifier: Double = 1.0
)
