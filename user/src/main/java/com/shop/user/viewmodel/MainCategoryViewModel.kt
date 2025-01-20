package com.shop.user.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shop.user.R
import com.shop.user.data.model.Banner
import com.shop.user.data.model.Item
import com.shop.user.data.model.Product
import timber.log.Timber

class MainCategoryViewModel : ViewModel() {
    /*========================================================================
        VARIABLES
    =========================================================================*/
    private val _banners = MutableLiveData<ArrayList<Banner>>()
    val banners: LiveData<ArrayList<Banner>> = _banners

    private val _bestSeller = MutableLiveData<List<Product>>()
    val bestSeller: LiveData<List<Product>> = _bestSeller

    private val _products = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>> = _products

    private val _dataList = MutableLiveData<MutableList<Item>>()
    val dataList: MutableLiveData<MutableList<Item>> = _dataList


    /*========================================================================
        FUNCTIONS
    =========================================================================*/
    fun fetchBanners() {
        Timber.i("get Banners")
        val bannerList = arrayListOf(
            Banner(R.drawable.nikon_canon_offer),
            Banner(R.drawable.offer_shoping),
            Banner(R.drawable.tv_offer)
        )
        updateBanners(bannerList)
    }

    fun fetchBestSeller() {
        Timber.i("get Best Seller")
        val bestSellerList = listOf(
            Product(R.drawable.bags, "Up to 20% off"),
            Product(R.drawable.mobiles, "Up to 10% off"),
            Product(R.drawable.watches, "Up to 40% off"),
            Product(R.drawable.bags, "Up to 20% off"),
            Product(R.drawable.bags, "Up to 20% off"),
            Product(R.drawable.mobiles, "Up to 10% off"),
            Product(R.drawable.watches, "Up to 40% off"),
            Product(R.drawable.bags, "Up to 20% off")
        )
        updateBestSeller(bestSellerList)
    }

    fun fetchProducts() {
        Timber.i("get Products")
        val productList = listOf(
            Product(R.drawable.levis_clothing, "Up to 25% off"),
            Product(R.drawable.women_clothing, "Up to 30% off"),
            Product(R.drawable.nikeshoes, "Up to 35% off"),
            Product(R.drawable.women_clothing, "Up to 30% off"),
            Product(R.drawable.nikeshoes, "Up to 35% off"),
            Product(R.drawable.levis_clothing, "Up to 25% off"),
            Product(R.drawable.women_clothing, "Up to 30% off"),
            Product(R.drawable.nikeshoes, "Up to 35% off"),
            Product(R.drawable.levis_clothing, "Up to 25% off"),
            Product(R.drawable.women_clothing, "Up to 30% off"),
            Product(R.drawable.nikeshoes, "Up to 35% off"),
            Product(R.drawable.women_clothing, "Up to 30% off"),
            Product(R.drawable.nikeshoes, "Up to 35% off"),
            Product(R.drawable.levis_clothing, "Up to 25% off"),
            Product(R.drawable.women_clothing, "Up to 30% off"),
            Product(R.drawable.nikeshoes, "Up to 35% off"),
            Product(R.drawable.levis_clothing, "Up to 25% off"),
            Product(R.drawable.women_clothing, "Up to 30% off"),
            Product(R.drawable.nikeshoes, "Up to 35% off"),
            Product(R.drawable.women_clothing, "Up to 30% off"),
            Product(R.drawable.nikeshoes, "Up to 35% off"),
            Product(R.drawable.levis_clothing, "Up to 25% off"),
            Product(R.drawable.women_clothing, "Up to 30% off"),
            Product(R.drawable.nikeshoes, "Up to 35% off"),
        )

        updateProducts(productList)
    }

    private fun updateBanners(banners: ArrayList<Banner>) {
        Timber.i("update Banners: $banners")
        _banners.postValue(banners)
    }

    private fun updateBestSeller(bestSeller: List<Product>) {
        Timber.i("update Best Seller: $bestSeller")
        _bestSeller.postValue(bestSeller)
    }

    private fun updateProducts(products: List<Product>) {
        Timber.i("update Products: $products")
        _products.postValue(products)
    }
}