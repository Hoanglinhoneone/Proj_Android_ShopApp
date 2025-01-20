package com.shop.user.ui

import com.shop.user.data.model.Product

interface OnItemClickListener {
    fun onItemClick(position: Int, product: Product)
}