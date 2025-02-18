package com.shop.user.data.repository

import com.shop.user.data.local.dao.BannerDao
import com.shop.user.data.model.Banner
import kotlinx.coroutines.flow.Flow

class BannerRepository(private val bannerDao: BannerDao) {
    suspend fun countBanners(): Int {
        return bannerDao.countBanners()
    }

    suspend fun insertBanners(banners: List<Banner>) {
        bannerDao.insertBanners(banners)
    }

    fun getBanners(): Flow<List<Banner>> {
        return bannerDao.getBanners()
    }
}
