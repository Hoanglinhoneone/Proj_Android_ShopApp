package com.example.user_app.ui.fragment.categories

import android.view.View
import com.example.user_app.R
import com.example.user_app.data.model.Banner
import com.example.user_app.data.model.DataItem
import com.example.user_app.data.model.DataItemType
import com.example.user_app.data.model.Product
import com.example.user_app.databinding.FragmentMainCategoryBinding
import com.example.user_app.ui.adapter.MainCategoryAdapter
import com.example.user_app.ui.fragment.BaseFragment

class MainCategoryFragment : BaseFragment() {
    private lateinit var binding: FragmentMainCategoryBinding
    private lateinit var dataList: ArrayList<DataItem>
    override fun getLayoutId(): Int {
        return R.layout.fragment_main_category
    }

    override fun setUpView(view: View) {
        binding = FragmentMainCategoryBinding.bind(view)

        dataList = ArrayList()
        setUpData()
        val adapter = MainCategoryAdapter(dataList)
        binding.recyclerview.adapter = adapter

    }

    private fun setUpData() {
        // banner
        val bannerList = ArrayList<Banner>()
        bannerList.add(Banner(R.drawable.nikon_canon_offer))
        bannerList.add(Banner(R.drawable.offer_shoping))
        bannerList.add(Banner(R.drawable.tv_offer))

        // bestseller
        val bestSellerList = ArrayList<Product>()
        bestSellerList.add(Product(R.drawable.bags , "Up to 20% off"))
        bestSellerList.add(Product(R.drawable.mobiles, "Up to 10% off"))
        bestSellerList.add(Product(R.drawable.watches, "Up to 40% off"))
        bestSellerList.add(Product(R.drawable.bags, "Up to 20% off"))
        bestSellerList.add(Product(R.drawable.mobiles, "Up to 10% off"))
        bestSellerList.add(Product(R.drawable.watches, "Up to 40% off"))

        // product
        val productList = ArrayList<Product>()
        productList.add(Product(R.drawable.levis_clothing, "Up to 25% off"))
        productList.add(Product(R.drawable.women_clothing, "Up to 30% off"))
        productList.add(Product(R.drawable.nikeshoes, "Up to 35% off"))
        productList.add(Product(R.drawable.levis_clothing, "Up to 25% off"))
        productList.add(Product(R.drawable.women_clothing, "Up to 30% off"))
        productList.add(Product(R.drawable.nikeshoes, "Up to 35% off"))

        // dataList
        dataList.add(DataItem(DataItemType.LIST_BANNER, bannerList))
        dataList.add(DataItem(DataItemType.BEST_SELLER, bestSellerList))
        dataList.add(DataItem(DataItemType.LIST_PRODUCT, productList))
    }
}