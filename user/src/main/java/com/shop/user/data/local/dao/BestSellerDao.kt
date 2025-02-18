package com.shop.user.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.shop.user.data.model.BestSeller
import kotlinx.coroutines.flow.Flow

@Dao
interface BestSellerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBestSellers(bestSellers: List<BestSeller>)

    @Query("SELECT * FROM best_seller")
    fun getBestSellers(): Flow<List<BestSeller>>

    @Query("DELETE FROM best_seller")
    suspend fun deleteAllBestSellers()

    @Query("SELECT COUNT(*) FROM best_seller")
    suspend fun countBestSellers(): Int
}