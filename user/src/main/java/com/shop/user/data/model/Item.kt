package com.shop.user.data.model

data class Item(val viewType: Int) {

    var recyclerItemList: List<Product>? = null
    var banners: ArrayList<Banner>? = null

    constructor(viewType: Int, recyclerItemList: List<Product>) : this(viewType) {
        this.recyclerItemList = recyclerItemList
    }

    constructor(viewType: Int, banners: ArrayList<Banner>) : this(viewType) {
        this.banners = banners
    }

    companion object {
        const val LIST_BANNER = 0
        const val BEST_SELLER = 1
        const val LIST_PRODUCT = 2
    }
}

data class Product(val image: Int, val offer: String)
data class Banner(val image: Int)
