package com.shop.user.ui.adapter.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shop.user.data.model.Item
import com.shop.user.data.model.Product
import com.shop.user.databinding.ItemBestSellerBinding
import com.shop.user.databinding.ItemProductBinding
import com.shop.user.ui.OnItemClickListener

class ChildAdapter(
    private val viewType: Int,
    private val productList: List<Product>,
    private val onItemClickListener: OnItemClickListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    /*========================================================================
        OVERRIDDEN METHODS
    =========================================================================*/
    override fun getItemViewType(position: Int): Int {
        return viewType
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            Item.BEST_SELLER -> {
                val binding = ItemBestSellerBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                return BestSellerViewHolder(binding)
            }

            else -> {
                val binding =
                    ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return ClothingViewHolder(binding)
            }
        }
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is BestSellerViewHolder -> {
                holder.bindBestSellerView(productList[position])
                holder.itemView.setOnClickListener {
                    onItemClickListener.onItemClick(position, viewType)
                }
            }

            else -> {
                (holder as ClothingViewHolder).bindClothingView(productList[position])
                holder.itemView.setOnClickListener {
                    onItemClickListener.onItemClick(position, viewType)
                }
            }
        }
    }

    /*========================================================================
        INNER CLASSES
    =========================================================================*/
    inner class BestSellerViewHolder(private val binding: ItemBestSellerBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindBestSellerView(productList: Product) {
            binding.bestSellerImage.setImageResource(productList.image)
            binding.bestSellerText.text = productList.offer
        }
    }

    inner class ClothingViewHolder(private val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindClothingView(productList: Product) {
            binding.clothingImage.setImageResource(productList.image)
            binding.clothingOfferTv.text = productList.offer
        }
    }
}