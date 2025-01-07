package com.example.user_app.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.user_app.data.model.Banner
import com.example.user_app.databinding.ItemBannerBinding
import com.example.user_app.databinding.LayoutBannerBinding

class BannerPagerAdapter(
    private val itemList: List<Banner>,
    
) : RecyclerView.Adapter<BannerPagerAdapter.BannerViewHolder>() {

    inner class BannerViewHolder(private val binding: ItemBannerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindBannerView(banner: Banner) {
            binding.bannerIv.setImageResource(banner.image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        val binding = ItemBannerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BannerViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {
        holder.bindBannerView(itemList[position])
    }
}