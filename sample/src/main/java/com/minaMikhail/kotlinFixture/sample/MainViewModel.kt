package com.minaMikhail.kotlinFixture.sample

import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    fun getProductQuantity(product: Product): Int {
        return product.quantity
    }

    fun calculateTotal(items: List<Product>): Int {
        var total = 0

        for (item in items) {
            total += item.price * item.quantity
        }

        return total
    }
}