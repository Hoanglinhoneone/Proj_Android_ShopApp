package com.shop.user.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

data class Item(
    val viewType: Int,
    val products: List<Product>? = null,
    val bestSellers: List<BestSeller>? = null,
    val banners: List<Banner>? = null) {

    companion object {
        const val LIST_BANNER = 0
        const val BEST_SELLER = 1
        const val LIST_PRODUCT = 2
    }
}
@Entity(tableName = "products")
data class Product(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo val image: Int,
    @ColumnInfo val offer: String)

@Entity(tableName = "best_seller")
data class BestSeller(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo val image: Int,
    @ColumnInfo val offer: String
)

@Entity(tableName = "banners")
data class Banner(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo val image: Int
)
