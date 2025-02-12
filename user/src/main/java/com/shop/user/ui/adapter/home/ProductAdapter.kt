package com.shop.user.ui.adapter.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shop.user.data.model.Product
import com.shop.user.databinding.ItemProductBinding
import com.shop.user.ui.OnItemClickListener

class ProductAdapter(
    private val viewType: Int,
    private val productList: List<Product>,
    private val onItemClickListener: OnItemClickListener
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {
    /*========================================================================
        OVERRIDDEN METHODS
    =========================================================================*/
    override fun getItemViewType(position: Int): Int {
        return viewType
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductAdapter.ProductViewHolder {
        val binding =
            ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductAdapter.ProductViewHolder, position: Int) {
        holder.bindProductView(productList[position])
        onItemClickListener.onItemClick(productList[position].id, viewType)
    }

    /*========================================================================
        INNER CLASSES
    =========================================================================*/
    inner class ProductViewHolder(private val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindProductView(product: Product) {
            binding.clothingImage.setImageResource(product.image)
            binding.clothingOfferTv.text = product.offer
        }
    }
}