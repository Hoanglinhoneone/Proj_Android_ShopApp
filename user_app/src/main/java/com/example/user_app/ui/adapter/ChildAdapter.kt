package com.example.user_app.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.user_app.data.model.Banner
import com.example.user_app.data.model.DataItemType
import com.example.user_app.data.model.Product
import com.example.user_app.databinding.ItemBannerBinding
import com.example.user_app.databinding.ItemBestSellerBinding
import com.example.user_app.databinding.ItemProductBinding

class ChildAdapter(
    private val viewType: Int,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var itemList: List<Product> = emptyList()
    private var itemListBanner: List<Banner> = emptyList()

    constructor(viewType: Int, itemList: List<Product>) : this(viewType) {
        this.itemList = itemList
    }
    constructor(viewType: Int, itemListBanner: ArrayList<Banner>) : this(viewType) {
        this.itemListBanner = itemListBanner
    }

    inner class BannerViewHolder(private val binding: ItemBannerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindBannerView(banner: Banner) {
            binding.bannerIv.setImageResource(banner.image)
        }
    }

    inner class BestSellerViewHolder(private val binding: ItemBestSellerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindBestSellerView(product: Product) {
            binding.bestSellerImage.setImageResource(product.image)
            binding.bestSellerText.text = product.offer
        }
    }

    inner class ProductViewHolder(private val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindProductView(product: Product) {
            binding.clothingImage.setImageResource(product.image)
            binding.clothingOfferTv.text = product.offer
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            DataItemType.LIST_BANNER -> {
                val binding = ItemBannerBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return BannerViewHolder(binding)
            }
            DataItemType.BEST_SELLER -> {
                val binding = ItemBestSellerBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return BestSellerViewHolder(binding)
            }
            else -> {
                val binding = ItemProductBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false)
                return ProductViewHolder(binding)
            }
        }
    }

    override fun getItemCount(): Int {
        return when(viewType) {
            DataItemType.LIST_BANNER -> itemListBanner.size
            else -> itemList.size
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is BestSellerViewHolder -> {
                holder.bindBestSellerView(itemList[position])
            }
            is ProductViewHolder -> {
                holder.bindProductView(itemList[position])
            }
            is BannerViewHolder -> {
                holder.bindBannerView(itemListBanner[position])
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return viewType
    }
}