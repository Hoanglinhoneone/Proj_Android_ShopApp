package com.shop.user.data.datasource

import com.shop.user.R
import com.shop.user.data.model.Banner
import com.shop.user.data.model.BestSeller
import com.shop.user.data.model.Product

object DataSource {

    val getProducts = listOf(
        Product(image = R.drawable.levis_clothing, offer = "Up to 25% off"),
        Product(image = R.drawable.women_clothing, offer = "Up to 30% off"),
        Product(image = R.drawable.nikeshoes, offer = "Up to 35% off"),
        Product(image = R.drawable.women_clothing, offer = "Up to 30% off"),
        Product(image = R.drawable.nikeshoes, offer = "Up to 35% off"),
        Product(image = R.drawable.levis_clothing, offer = "Up to 25% off"),
        Product(image = R.drawable.women_clothing, offer = "Up to 30% off"),
        Product(image = R.drawable.nikeshoes, offer = "Up to 35% off"),
        Product(image = R.drawable.levis_clothing, offer = "Up to 25% off"),
        Product(image = R.drawable.women_clothing, offer = "Up to 30% off"),
        Product(image = R.drawable.nikeshoes, offer = "Up to 35% off"),
        Product(image = R.drawable.women_clothing, offer = "Up to 30% off"),
        Product(image = R.drawable.nikeshoes, offer = "Up to 35% off"),
        Product(image = R.drawable.levis_clothing, offer = "Up to 25% off"),
        Product(image = R.drawable.women_clothing, offer = "Up to 30% off"),
        Product(image = R.drawable.nikeshoes, offer = "Up to 35% off"),
        Product(image = R.drawable.levis_clothing, offer = "Up to 25% off"),
        Product(image = R.drawable.women_clothing, offer = "Up to 30% off"),
        Product(image = R.drawable.nikeshoes, offer = "Up to 35% off"),
        Product(image = R.drawable.women_clothing, offer = "Up to 30% off"),
        Product(image = R.drawable.nikeshoes, offer = "Up to 35% off"),
        Product(image = R.drawable.levis_clothing, offer = "Up to 25% off"),
        Product(image = R.drawable.women_clothing, offer = "Up to 30% off"),
        Product(image = R.drawable.nikeshoes, offer = "Up to 35% off"),
    )

    val getBestSeller = listOf(
        BestSeller(image = R.drawable.bags, offer = "Up to 20% off"),
        BestSeller(image = R.drawable.mobiles, offer = "Up to 10% off"),
        BestSeller(image = R.drawable.watches, offer = "Up to 40% off"),
        BestSeller(image = R.drawable.bags, offer = "Up to 20% off"),
        BestSeller(image = R.drawable.bags, offer = "Up to 20% off"),
        BestSeller(image = R.drawable.mobiles, offer = "Up to 10% off"),
        BestSeller(image = R.drawable.watches, offer = "Up to 40% off"),
        BestSeller(image = R.drawable.bags, offer = "Up to 20% off")
    )

    val getBanners = listOf(
        Banner(image = R.drawable.nikon_canon_offer),
        Banner(image = R.drawable.offer_shoping),
        Banner(image = R.drawable.tv_offer)
    )
}