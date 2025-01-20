package com.shop.user.ui.adapter.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shop.user.data.model.Product
import com.shop.user.databinding.ItemProductBinding

class ChairAdapter(private val listChair: ArrayList<Product>)
    : RecyclerView.Adapter<ChairAdapter.ChairViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ChairViewHolder {
        val binding = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChairViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChairViewHolder, position: Int) {
        holder.binProductView(listChair[position])
    }

    override fun getItemCount(): Int {
        return listChair.size
    }

    inner class ChairViewHolder(private val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun binProductView(product: Product) {
                binding.clothingImage.setImageResource(product.image)
                binding.clothingOfferTv.text = product.offer
            }
        }
}