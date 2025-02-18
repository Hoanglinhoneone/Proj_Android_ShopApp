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
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
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

    private val _bannerState = MutableStateFlow<UiState<List<Banner>>>(UiState.Loading)
    val bannerState: StateFlow<UiState<List<Banner>>> = _bannerState

    private val _bestSellerState = MutableStateFlow<UiState<List<BestSeller>>>(UiState.Loading)
    val bestSellerState: StateFlow<UiState<List<BestSeller>>> = _bestSellerState

    private val _productState = MutableStateFlow<UiState<List<Product>>>(UiState.Loading)
    val productState: StateFlow<UiState<List<Product>>> = _productState



    private val _dataList = MutableLiveData<MutableList<Item>>()
    val dataList: MutableLiveData<MutableList<Item>> = _dataList

    init {
        saveData()
    }

    private fun saveData() {
        // Check if data already exists in the database
        viewModelScope.launch {
            if (productRepository.countProducts() == 0) {
                Timber.i("save data product into room")
                productRepository.insertProducts(DataSource.getProducts)
            } else {
                Timber.i("data product already exist")
                loadProducts()
            }
            if (bannerRepository.countBanners() == 0) {
                Timber.i("save data banner into room")
                bannerRepository.insertBanners(DataSource.getBanners)
            } else {
                Timber.i("data banner already exist")
                loadBanners()
            }
            if (bestSellerRepository.countBestSellers() == 0) {
                Timber.i("save data best seller into room")
                bestSellerRepository.insertBestSellers(DataSource.getBestSeller)
            } else {
                Timber.i("data best seller already exist")
                loadBestSellers()
            }
        }
    }

    private fun loadProducts() {
        viewModelScope.launch {
            _productState.value = UiState.Loading
            try {
                val products = productRepository.getAllProducts()
                _productState.value = UiState.Success(products)
            } catch (e: Exception) {
                _productState.value = UiState.Error(e)
            }
        }
    }

    private fun loadBestSellers() {
        viewModelScope.launch {
            _bestSellerState.value = UiState.Loading
            try {
                val bestSellers = bestSellerRepository.getBestSellers()
                _bestSellerState.value = UiState.Success(bestSellers)
            } catch (e: Exception) {
                _bestSellerState.value = UiState.Error(e)
            }
        }
    }

    private fun loadBanners() {
        viewModelScope.launch {
            _bannerState.value = UiState.Loading
            try {
                val banners = bannerRepository.getBanners()
                _bannerState.value = UiState.Success(banners)
            } catch (e: Exception) {
                _bannerState.value = UiState.Error(e)
            }
        }
    }

    /*========================================================================
        FUNCTIONS
    =========================================================================*/
//    private fun loadingBanner() {
//        viewModelScope.launch {
//            bannerRepository.getBanners().collect { banners ->
//
//        }
//    }
}

sealed class UiState<out T> {
    object Loading : UiState<Nothing>()
    data class Success<out T>(val data: Flow<T>) : UiState<T>()
    data class Error(val exception: Exception) : UiState<Nothing>()
}