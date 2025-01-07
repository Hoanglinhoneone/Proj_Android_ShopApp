package com.example.user_app.data.model

data class DataItem(val viewType: Int) {

    var recyclerItemList: List<Product>? = null
    var banners: ArrayList<Banner>? = null

    constructor(viewType: Int, recyclerItemList: List<Product>) : this(viewType) {
        this.recyclerItemList = recyclerItemList
    }
    constructor(viewType: Int, banners: ArrayList<Banner>) : this(viewType) {
        this.banners = banners
    }
}

data class Product(val image: Int, val offer: String)
data class Banner(val image: Int)
