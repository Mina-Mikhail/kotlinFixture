package com.minaMikhail.kotlinFixture.sample

data class Product(
    val id: Int,
    val price: Int,
    val quantity: Int,
    val productType: ProductType
)
sealed class ProductType {
    data class Electronics(val maxVoltage: Int) : ProductType()
    data object Food : ProductType()
}