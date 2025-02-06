package com.shop.user.ui.fragment.shopping.home.categories

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.shop.user.R
import com.shop.user.data.model.Item
import com.shop.user.databinding.FragmentMainCategoryBinding
import com.shop.user.ui.OnItemClickListener
import com.shop.user.ui.adapter.home.MainCategoryAdapter
import com.shop.user.ui.common.showSnackBarShort
import com.shop.user.ui.fragment.BaseFragment
import com.shop.user.viewmodel.home.MainCategoryViewModel
import timber.log.Timber


class MainCategoryFragment :
    BaseFragment<FragmentMainCategoryBinding>(FragmentMainCategoryBinding::inflate),
    OnItemClickListener {
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
            adapter = MainCategoryAdapter(it, this)
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

    override fun onItemClick(position: Int, viewType: Int) {
        Timber.d("onItemClick: $position, $viewType")
        when (viewType) {
            Item.LIST_BANNER -> {
                Timber.d("Banner clicked")
//                toastShort("Banner clicked", requireContext())
                showSnackBarShort("Banner clicked", binding.root)
            }

            Item.BEST_SELLER -> {
                Timber.d("Best seller clicked")
//                toastShort("Best seller clicked", requireContext())
//                Toast.makeText(context, "Best seller clicked", Toast.LENGTH_SHORT).show()
                Toast.makeText(context, "Best seller clicked", Toast.LENGTH_SHORT).show()
                showSnackBarShort("Best seller clicked", binding.root)
                val productId = 1
                val bundle = Bundle().apply {
                    putInt("productId", productId)
                }
//                val action = HomeFragmentDirections.actionHomeFragmentToProductDetailFragment()
                findNavController().navigate(
                    R.id.action_homeFragment_to_productDetailFragment,
                    bundle
                )
            }

            else -> {
                Timber.d("Product clicked")
//                toastShort("Product clicked", requireContext())
//                Toast.makeText(context, "Product clicked", Toast.LENGTH_SHORT).show()
                showSnackBarShort("Product clicked", binding.root)
                findNavController().navigate(R.id.action_homeFragment_to_productDetailFragment)
            }
        }
    }

    /*========================================================================
          FUNCTIONS
     =========================================================================*/
}