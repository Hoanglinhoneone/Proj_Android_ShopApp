package com.shop.user.data.repository

import androidx.lifecycle.LiveData
import com.shop.user.data.local.dao.ProductDao
import com.shop.user.data.model.Product

class ProductRepository(private val productDao: ProductDao) {

    suspend fun countProducts(): Int {
        return productDao.countProducts()
    }

    fun getAllProducts(): LiveData<List<Product>> {
        return productDao.getAllProducts()
    }

    suspend fun insertProducts(products: List<Product>) {
        productDao.insertProducts(products)
    }
}
