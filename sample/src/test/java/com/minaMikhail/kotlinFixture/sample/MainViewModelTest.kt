package com.minaMikhail.kotlinFixture.sample

import com.minaMikhail.kotlinFixture.fixture
import com.minaMikhail.kotlinFixture.fixtureList
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class MainViewModelTest {

    private lateinit var mainViewModel: MainViewModel

    @Before
    fun setUp() {
        mainViewModel = MainViewModel()
    }

    @Test
    fun test_getProductQuantity() {
        val product = fixture<Product> {
            mapOf("price" to 350)
        }

        val expectedResult = product.quantity

        val actualResult = mainViewModel.getProductQuantity(product)

        assertEquals(expectedResult, actualResult)
    }

    @Test
    fun test_calculateTotal() {
        val cartItems = fixtureList<Product>(repeatSize = 2)

        val expectedResult =
            cartItems[0].price * cartItems[0].quantity + cartItems[1].price * cartItems[1].quantity

        val actualResult = mainViewModel.calculateTotal(cartItems)

        assertEquals(expectedResult, actualResult)
    }
}