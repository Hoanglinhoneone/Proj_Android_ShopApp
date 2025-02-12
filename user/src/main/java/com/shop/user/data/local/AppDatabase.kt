package com.shop.user.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.shop.user.data.local.dao.BannerDao
import com.shop.user.data.local.dao.BestSellerDao
import com.shop.user.data.local.dao.ProductDao
import com.shop.user.data.model.Banner
import com.shop.user.data.model.BestSeller
import com.shop.user.data.model.Product

@Database(entities = [Product::class, Banner::class, BestSeller::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
    abstract fun bannerDao(): BannerDao
    abstract fun bestSellerDao(): BestSellerDao

}