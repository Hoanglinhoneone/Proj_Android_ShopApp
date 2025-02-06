package com.shop.user.ui.adapter.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shop.user.R
import com.shop.user.data.model.Banner
import com.shop.user.data.model.Item
import com.shop.user.data.model.Product
import com.shop.user.databinding.ItemRecyclerviewHorizontalBinding
import com.shop.user.databinding.ItemRecyclerviewVerticalBinding
import com.shop.user.databinding.LayoutBannerBinding
import com.shop.user.ui.OnItemClickListener

class MainCategoryAdapter(
    private var itemList: MutableList<Item>,
    private val onItemClickListener: OnItemClickListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    /*========================================================================
        OVERRIDDEN METHODS
    =========================================================================*/
    override fun getItemViewType(position: Int): Int {
        return when (itemList[position].viewType) {
            Item.LIST_BANNER -> R.layout.layout_banner
            Item.BEST_SELLER -> R.layout.item_best_seller
            Item.LIST_PRODUCT -> R.layout.item_product
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            R.layout.layout_banner -> {
                val binding = LayoutBannerBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return BannerViewHolder(binding)
            }

            R.layout.item_best_seller -> {
                val binding = ItemRecyclerviewHorizontalBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return BestSellerViewHolder(binding)
            }

            else -> {
                val binding = ItemRecyclerviewVerticalBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return ProductViewHolder(binding)
            }
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is BannerViewHolder -> {
                itemList[position].banners?.let {
                    holder.bindBannerView(it)
                }
            }

            is BestSellerViewHolder -> {
                itemList[position].recyclerItemList?.let {
                    holder.bindBestSellerView(it)
                }
            }

            is ProductViewHolder -> {
                itemList[position].recyclerItemList?.let {
                    holder.bindProductView(it)
                }
            }
        }
    }

    /*========================================================================
        FUNCTIONS
    =========================================================================*/
//    @SuppressLint("NotifyDataSetChanged")
//    fun updateData(dataList: List<Item>) {
//        itemList.clear()
//        itemList.addAll(dataList)
//        notifyDataSetChanged()
//    }

    /*========================================================================
        OVERRIDDEN METHODS
    =========================================================================*/
    inner class BannerViewHolder(private val binding: LayoutBannerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindBannerView(banners: ArrayList<Banner>) {
            val adapter = BannerAdapter(banners, onItemClickListener)
            binding.viewPager2.adapter = adapter
            binding.dotsIndicator.attachTo(binding.viewPager2)
        }
    }

    inner class BestSellerViewHolder(private val binding: ItemRecyclerviewHorizontalBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.rclHorizontal.setHasFixedSize(true)
            binding.rclHorizontal.layoutManager =
                LinearLayoutManager(binding.root.context, LinearLayoutManager.HORIZONTAL, false)
        }

        @SuppressLint("SetTextI18n")
        fun bindBestSellerView(products: List<Product>) {
            binding.title.text = "Best seller"
            val adapter = ChildAdapter(Item.BEST_SELLER, products, onItemClickListener)
            binding.rclHorizontal.adapter = adapter
        }
    }

    inner class ProductViewHolder(private val binding: ItemRecyclerviewVerticalBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.rclVertical.setHasFixedSize(true)
            binding.rclVertical.layoutManager =
                GridLayoutManager(binding.root.context, 3, GridLayoutManager.VERTICAL, false)
            binding.rclVertical.isNestedScrollingEnabled = false
        }

        @SuppressLint("SetTextI18n")
        fun bindProductView(products: List<Product>) {
            binding.title.text = "Product"
            val adapter = ChildAdapter(Item.LIST_PRODUCT, products, onItemClickListener)
            binding.rclVertical.adapter = adapter
        }
    }
}
