package com.shop.user.viewmodel.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import timber.log.Timber

class ProductDetailViewModel : ViewModel() {
    private val _idProduct = MutableLiveData<Int>()
    val product: LiveData<Int> get() = _idProduct

    fun setProduct(idProduct: Int) {
        Timber.i("setProduct: $product")
        _idProduct.value = idProduct
    }
}