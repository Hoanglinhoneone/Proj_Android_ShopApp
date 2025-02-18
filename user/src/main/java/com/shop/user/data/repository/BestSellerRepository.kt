package com.shop.user.data.repository

import androidx.lifecycle.LiveData
import com.shop.user.data.local.dao.BestSellerDao
import com.shop.user.data.model.BestSeller
import kotlinx.coroutines.flow.Flow

class BestSellerRepository(private val bestSellerDao: BestSellerDao) {
    suspend fun insertBestSellers(bestSellers: List<BestSeller>) {
        bestSellerDao.insertBestSellers(bestSellers)
    }

    fun getBestSellers(): Flow<List<BestSeller>> {
        return bestSellerDao.getBestSellers()
    }

    suspend fun deleteAllBestSellers() {
        bestSellerDao.deleteAllBestSellers()
    }

    suspend fun countBestSellers(): Int {
        return bestSellerDao.countBestSellers()
    }

}