package com.shop.user.ui.adapter.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.shop.user.R
import com.shop.user.data.model.Banner
import com.shop.user.data.model.Item
import com.shop.user.ui.OnItemClickListener

class BannerAdapter(
    private val listBanner: List<Banner>,
    private val onItemClickListener: OnItemClickListener
    ) :
    RecyclerView.Adapter<BannerAdapter.BannerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_banner, parent, false)
        return BannerViewHolder(view)
    }

    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {
        holder.bind(listBanner[position])
        holder.itemView.setOnClickListener {
            onItemClickListener.onItemClick(position, Item.LIST_BANNER)
        }
    }

    override fun getItemCount(): Int = listBanner.size

    inner class BannerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.bannerIv)
        fun bind(banner: Banner) {
            imageView.setImageResource(banner.image)
        }
    }
}