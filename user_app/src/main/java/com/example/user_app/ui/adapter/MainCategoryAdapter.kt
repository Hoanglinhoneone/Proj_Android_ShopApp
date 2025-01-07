package com.example.user_app.ui.adapter

import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.user_app.R
import com.example.user_app.data.model.Banner
import com.example.user_app.data.model.DataItem
import com.example.user_app.data.model.DataItemType
import com.example.user_app.data.model.Product
import com.example.user_app.databinding.ItemRecyclerviewHorizontalBinding
import com.example.user_app.databinding.ItemRecyclerviewVerticalBinding
import com.example.user_app.databinding.LayoutBannerBinding

class MainCategoryAdapter(
    private val dataItemList: List<DataItem>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class BannerViewHolder(private val binding: LayoutBannerBinding) :
        RecyclerView.ViewHolder(binding.root) {

        var autoScrollHandler: Handler? = null
        private val autoScrollRunnable = object : Runnable {
            override fun run() {
                binding.viewPager2.let { viewPager2 ->
                    val itemCount = viewPager2.adapter?.itemCount ?: 0
                    if (itemCount > 0) {
                        val nextItem = (viewPager2.currentItem + 1) % itemCount
                        viewPager2.setCurrentItem(nextItem, true)
                        autoScrollHandler?.postDelayed(this, 3000) // Cuộn lại sau 3 giây
                    }
                }
            }
        }

        fun bindBannerView(banners: ArrayList<Banner>) {
            val adapter = ChildAdapter(DataItemType.LIST_BANNER, banners)
            binding.viewPager2.adapter = adapter
            binding.dotsIndicator.setViewPager2(binding.viewPager2)

            startAutoScroll()

        }

        private fun startAutoScroll() {
            stopAutoScroll()
            autoScrollHandler = Handler(Looper.getMainLooper())
            autoScrollHandler?.postDelayed(autoScrollRunnable, 3000)
        }

        private fun stopAutoScroll() {
            autoScrollHandler?.removeCallbacks(autoScrollRunnable)
            autoScrollHandler = null
        }

        fun cleanup() {
            stopAutoScroll()
        }

    }

    inner class BestSellerViewHolder(private val binding: ItemRecyclerviewHorizontalBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.rclHorizontal.setHasFixedSize(true)
            binding.rclHorizontal.layoutManager =
                LinearLayoutManager(binding.root.context, LinearLayoutManager.HORIZONTAL, false)
        }

        fun bindBestSellerView(products: List<Product>) {
            val adapter = ChildAdapter(DataItemType.BEST_SELLER, products)
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

        fun bindProductView(products: List<Product>) {
            val adapter = ChildAdapter(DataItemType.LIST_PRODUCT, products)
            binding.rclVertical.adapter = adapter
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (dataItemList[position].viewType) {
            DataItemType.LIST_BANNER -> R.layout.layout_banner
            DataItemType.BEST_SELLER -> R.layout.item_best_seller
            DataItemType.LIST_PRODUCT -> R.layout.item_product
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
        return dataItemList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is BannerViewHolder -> {
                dataItemList[position].banners?.let {
                    holder.cleanup()
                    holder.bindBannerView(it)
                }
            }
            is BestSellerViewHolder -> {
                dataItemList[position].recyclerItemList?.let {
                    holder.bindBestSellerView(it)
                }
            }
            is ProductViewHolder -> {
                dataItemList[position].recyclerItemList?.let {
                    holder.bindProductView(it)
                }
            }
        }
    }
}
