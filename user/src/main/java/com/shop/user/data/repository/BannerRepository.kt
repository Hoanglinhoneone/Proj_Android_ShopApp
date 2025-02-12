package com.shop.user.data.repository

import androidx.lifecycle.LiveData
import com.shop.user.data.local.dao.BannerDao
import com.shop.user.data.model.Banner

class BannerRepository(private val bannerDao: BannerDao) {
    suspend fun countBanners(): Int {
        return bannerDao.countBanners()
    }

    suspend fun insertBanners(banners: List<Banner>) {
        bannerDao.insertBanners(banners)
    }

    fun getBanners(): LiveData<List<Banner>> {
        return bannerDao.getBanners()
    }
}