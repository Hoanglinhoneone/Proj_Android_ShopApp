package com.shop.user.ui.adapter.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shop.user.data.model.BestSeller
import com.shop.user.databinding.ItemBestSellerBinding
import com.shop.user.ui.OnItemClickListener

class BestSellerAdapter(
    private val viewType: Int,
    private val bestSellerList: List<BestSeller>,
    private val onItemClickListener: OnItemClickListener
) : RecyclerView.Adapter<BestSellerAdapter.BestSellerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            BestSellerAdapter.BestSellerViewHolder {
        val binding = ItemBestSellerBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return BestSellerViewHolder(binding)
    }

    override fun getItemViewType(position: Int): Int {
        return viewType
    }

    override fun onBindViewHolder(holder: BestSellerAdapter.BestSellerViewHolder, position: Int) {
        holder.bindBestSellerView(bestSellerList[position])
        holder.itemView.setOnClickListener {
            onItemClickListener.onItemClick(position, viewType = viewType)
        }
    }

    override fun getItemCount(): Int {
        return bestSellerList.size
    }

    inner class BestSellerViewHolder(private val binding: ItemBestSellerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindBestSellerView(bestSellerList: BestSeller) {
            binding.bestSellerImage.setImageResource(bestSellerList.image)
            binding.bestSellerText.text = bestSellerList.offer
        }
    }
}