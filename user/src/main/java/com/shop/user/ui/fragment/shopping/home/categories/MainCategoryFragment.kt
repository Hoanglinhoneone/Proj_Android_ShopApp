package com.shop.user.ui.fragment.shopping.home.categories

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.shop.user.R
import com.shop.user.data.model.Banner
import com.shop.user.data.model.Item
import com.shop.user.data.model.Product
import com.shop.user.databinding.FragmentMainCategoryBinding
import com.shop.user.ui.adapter.home.MainCategoryAdapter
import com.shop.user.ui.fragment.BaseFragment
import com.shop.user.viewmodel.MainCategoryViewModel
import timber.log.Timber


class MainCategoryFragment :
    BaseFragment<FragmentMainCategoryBinding>(FragmentMainCategoryBinding::inflate) {
    /*========================================================================
             VARIABLES
    =========================================================================*/
    private val viewModel by viewModels<MainCategoryViewModel>()
    private lateinit var adapter: MainCategoryAdapter

    /*========================================================================
     OVERRIDDEN METHODS
    =========================================================================*/
    override fun initView() {
        Timber.i("initView")
        binding.recyclerview.setHasFixedSize(true)
        viewModel.fetchBanners()
        viewModel.fetchBestSeller()
        viewModel.fetchProducts()
//        prepareData()
        binding.recyclerview.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }

    override fun observeData() {
        Timber.i("observeData")
        val dataList = mutableListOf<Item>()
        viewModel.dataList.observe(viewLifecycleOwner) {
            adapter = MainCategoryAdapter(it)
            binding.recyclerview.adapter = adapter
        }
        viewModel.banners.observe(viewLifecycleOwner) { banners ->
            Timber.d("banners: $banners")
            dataList.add(Item(Item.LIST_BANNER, banners))
            viewModel.dataList.value = dataList
        }
        viewModel.bestSeller.observe(viewLifecycleOwner) { bestSeller ->
            Timber.d("bestSeller: $bestSeller")
            dataList.add(Item(Item.BEST_SELLER, bestSeller))
            viewModel.dataList.value = dataList
        }
        viewModel.products.observe(viewLifecycleOwner) { products ->
            Timber.d("products: $products")
            dataList.add(Item(Item.LIST_PRODUCT, products))
            viewModel.dataList.value = dataList
        }
    }

    /*========================================================================
          FUNCTIONS
     =========================================================================*/
    private fun prepareData() {
        val bannerList = ArrayList<Banner>()
        bannerList.add(Banner(R.drawable.nikon_canon_offer))
        bannerList.add(Banner(R.drawable.offer_shoping))
        bannerList.add(Banner(R.drawable.tv_offer))

        // best seller
        val bestSellerList = ArrayList<Product>()
        bestSellerList.add(Product(R.drawable.bags, "Up to 20% off"))
        bestSellerList.add(Product(R.drawable.mobiles, "Up to 10% off"))
        bestSellerList.add(Product(R.drawable.watches, "Up to 40% off"))
        bestSellerList.add(Product(R.drawable.bags, "Up to 20% off"))
        bestSellerList.add(Product(R.drawable.mobiles, "Up to 10% off"))
        bestSellerList.add(Product(R.drawable.watches, "Up to 40% off"))

        //clothing
        val clothingList = ArrayList<Product>()
        clothingList.add(Product(R.drawable.levis_clothing, "Up to 25% off"))
        clothingList.add(Product(R.drawable.women_clothing, "Up to 30% off"))
        clothingList.add(Product(R.drawable.nikeshoes, "Up to 35% off"))
        clothingList.add(Product(R.drawable.levis_clothing, "Up to 25% off"))
        clothingList.add(Product(R.drawable.women_clothing, "Up to 30% off"))
        clothingList.add(Product(R.drawable.nikeshoes, "Up to 35% off"))
        clothingList.add(Product(R.drawable.levis_clothing, "Up to 25% off"))
        clothingList.add(Product(R.drawable.women_clothing, "Up to 30% off"))
        clothingList.add(Product(R.drawable.nikeshoes, "Up to 35% off"))
        clothingList.add(Product(R.drawable.levis_clothing, "Up to 25% off"))
        clothingList.add(Product(R.drawable.women_clothing, "Up to 30% off"))
        clothingList.add(Product(R.drawable.nikeshoes, "Up to 35% off"))
        clothingList.add(Product(R.drawable.levis_clothing, "Up to 25% off"))
        clothingList.add(Product(R.drawable.women_clothing, "Up to 30% off"))
        clothingList.add(Product(R.drawable.nikeshoes, "Up to 35% off"))
        clothingList.add(Product(R.drawable.levis_clothing, "Up to 25% off"))
        clothingList.add(Product(R.drawable.women_clothing, "Up to 30% off"))
        clothingList.add(Product(R.drawable.nikeshoes, "Up to 35% off"))
        clothingList.add(Product(R.drawable.levis_clothing, "Up to 25% off"))
        clothingList.add(Product(R.drawable.women_clothing, "Up to 30% off"))
        clothingList.add(Product(R.drawable.nikeshoes, "Up to 35% off"))
        clothingList.add(Product(R.drawable.levis_clothing, "Up to 25% off"))
        clothingList.add(Product(R.drawable.women_clothing, "Up to 30% off"))
        clothingList.add(Product(R.drawable.nikeshoes, "Up to 35% off"))

//        dataList.add(Item(Item.LIST_BANNER, bannerList))
//        dataList.add(Item(Item.BEST_SELLER, bestSellerList.asReversed()))
//        dataList.add(Item(Item.LIST_PRODUCT, clothingList))
    }
}