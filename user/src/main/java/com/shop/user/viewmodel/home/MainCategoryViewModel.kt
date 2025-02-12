package com.shop.user.viewmodel.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shop.user.common.MyApplication
import com.shop.user.data.datasource.DataSource
import com.shop.user.data.model.Banner
import com.shop.user.data.model.BestSeller
import com.shop.user.data.model.Item
import com.shop.user.data.model.Product
import com.shop.user.data.repository.BannerRepository
import com.shop.user.data.repository.BestSellerRepository
import com.shop.user.data.repository.ProductRepository
import kotlinx.coroutines.launch
import timber.log.Timber

class MainCategoryViewModel : ViewModel() {
    /*========================================================================
        VARIABLES
    =========================================================================*/
    private val productDao = MyApplication.appDatabase.productDao()
    private val productRepository = ProductRepository(productDao)

    private val bannerDao = MyApplication.appDatabase.bannerDao()
    private val bannerRepository = BannerRepository(bannerDao)

    private val bestSellerDao = MyApplication.appDatabase.bestSellerDao()
    private val bestSellerRepository = BestSellerRepository(bestSellerDao)

    val banners: LiveData<List<Banner>> = bannerRepository.getBanners()

    val bestSeller: LiveData<List<BestSeller>> = bestSellerRepository.getBestSellers()

    val products: LiveData<List<Product>> = productRepository.getAllProducts()

    private val _dataList = MutableLiveData<MutableList<Item>>()
    val dataList: MutableLiveData<MutableList<Item>> = _dataList

    init {
        viewModelScope.launch {
            if (productRepository.countProducts() == 0) {
                Timber.i("save data product into room")
                productRepository.insertProducts(DataSource.getProducts)
            }
            if (bannerRepository.countBanners() == 0) {
                Timber.i("save data banner into room")
                bannerRepository.insertBanners(DataSource.getBanners)
            }
            if (bestSellerRepository.countBestSellers() == 0) {
                Timber.i("save data best seller into room")
                bestSellerRepository.insertBestSellers(DataSource.getBestSeller)
            }
        }
    }

    /*========================================================================
        FUNCTIONS
    =========================================================================*/
}