package com.shop.user.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shop.user.data.model.Product

class CartViewModel() : ViewModel() {
    private val _cartItems = MutableLiveData<MutableList<Product>>()
    val cartItems: MutableLiveData<MutableList<Product>> = _cartItems

    private val _totalPrice = MutableLiveData<Double>()
    val totalPrice: MutableLiveData<Double> = _totalPrice

    private val _totalQuantity = MutableLiveData<Int>()
    val totalQuantity: MutableLiveData<Int> = _totalQuantity

    private val _isCartEmpty = MutableLiveData<Boolean>()
    val isCartEmpty: MutableLiveData<Boolean> = _isCartEmpty

    init {
        _cartItems.value = mutableListOf()
        _totalPrice.value = 0.0
        _totalQuantity.value = 0
        _isCartEmpty.value = true
    }

}