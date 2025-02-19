package com.shop.user.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.shop.user.data.model.Banner
import kotlinx.coroutines.flow.Flow

@Dao
interface BannerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBanners(banners: List<Banner>)

    @Query("SELECT * FROM banners")
    fun getBanners(): Flow<List<Banner>>

    @Query("DELETE FROM banners")
    suspend fun deleteAllBanners()

    @Query("SELECT COUNT(*) FROM banners")
    suspend fun countBanners(): Int
}