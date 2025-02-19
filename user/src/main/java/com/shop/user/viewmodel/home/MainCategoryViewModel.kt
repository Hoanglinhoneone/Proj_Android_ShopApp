package com.shop.user.viewmodel.home

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
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
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

    private val _dataList = MutableLiveData<MutableList<Item>>()
    val dataList: MutableLiveData<MutableList<Item>> = _dataList

    val uiState = combine(
        bannerRepository.getBanners(),
        bestSellerRepository.getBestSellers(),
        productRepository.getAllProducts()
    ) { bannerState, bestSellerState, productState ->
        State.Success(bannerState, bestSellerState, productState)
    }.catch {
        State.Error(it.message ?: "Unknown error")
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = State.Loading
    )

    init {
        createData()
    }

    /*========================================================================
        FUNCTIONS
    =========================================================================*/
    private fun createData() {
        // Check if data already exists in the database
        viewModelScope.launch {
            if (bannerRepository.countBanners() == 0) {
                Timber.i("save data banner into room")
                bannerRepository.insertBanners(DataSource.getBanners)
            }
            Timber.i("Banner loaded")

            if (bestSellerRepository.countBestSellers() == 0) {
                Timber.i("save data best seller into room")
                bestSellerRepository.insertBestSellers(DataSource.getBestSeller)
            }
            Timber.i("Best Sellers loaded")

            if (productRepository.countProducts() == 0) {
                Timber.i("save data product into room")
                productRepository.insertProducts(DataSource.getProducts)
            }
            Timber.i("Products loaded")
        }
    }
}

sealed class State {
    data object Loading : State()

    data class Success(
        val banners: List<Banner>,
        val bestSellers: List<BestSeller>,
        val products: List<Product>
    ) : State()

    data class Error(val message: String) : State()
}