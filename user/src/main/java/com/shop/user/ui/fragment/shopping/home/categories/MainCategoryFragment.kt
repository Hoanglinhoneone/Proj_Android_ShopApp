package com.shop.user.ui.fragment.shopping.home.categories

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
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
import com.shop.user.viewmodel.home.UiState
import kotlinx.coroutines.launch
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
//        lifecycleScope.launch {
//            viewModel.banners.collect { banners ->
//                Timber.d("banners: $banners")
//                dataList.add(Item(Item.LIST_BANNER, banners = banners))
//                viewModel.dataList.value = dataList
//            }
//        }
        lifecycleScope.launch {
            viewModel.bannerState.collect { state ->
                when (state) {
                    is UiState.Loading -> {
                        Timber.i("Loading")
                    }

                    is UiState.Success -> {
                        state.data.collect { banners ->
                            Timber.d("Loading success banners: $banners")
                            dataList.add(Item(Item.LIST_BANNER, banners = banners))
                            viewModel.dataList.value = dataList
                        }
                    }

                    is UiState.Error -> {
                        Timber.d("Error: ${state.exception}")
                    }
                }
            }
        }
        lifecycleScope.launch {
            viewModel.bestSellerState.collect { state ->
                when (state) {
                    is UiState.Loading -> {
                        Timber.i("Loading")
                    }
                    is UiState.Success -> {
                        state.data.collect { bestSellers ->
                            Timber.d("Loading success")
                            dataList.add(Item(Item.BEST_SELLER, bestSellers = bestSellers))
                            viewModel.dataList.value = dataList
                        }
                    }
                    is UiState.Error -> {
                        Timber.d("Error: ${state.exception}")
                    }
                }
            }
        }
//        viewModel.bestSeller.observe(viewLifecycleOwner) { bestSeller ->
//            Timber.d("bestSeller: $bestSeller")
//            dataList.add(Item(Item.BEST_SELLER, bestSellers = bestSeller))
//            viewModel.dataList.value = dataList
//        }
        lifecycleScope.launch {
            viewModel.productState.collect { state ->
                when (state) {
                    is UiState.Loading -> {
                        Timber.i("Loading")
                    }
                    is UiState.Success -> {
                        state.data.collect { products ->
                            Timber.d("Loading success")
                            dataList.add(Item(Item.LIST_PRODUCT, products = products))
                            viewModel.dataList.value = dataList
                        }
                    }
                    is UiState.Error -> {
                        Timber.d("Error: ${state.exception}")
                    }
                }
            }
        }
    }

    override fun onItemClick(position: Int, viewType: Int) {
        Timber.d("onItemClick: $position, $viewType")
        when (viewType) {
            Item.LIST_BANNER -> {
                Timber.d("Banner clicked")
                showSnackBarShort("Banner clicked", binding.root)
            }

            Item.BEST_SELLER -> {
                Timber.d("Best seller clicked")
                Toast.makeText(context, "Best seller clicked", Toast.LENGTH_SHORT).show()
                showSnackBarShort("Best seller clicked", binding.root)
                val productId = 1
                val bundle = Bundle().apply {
                    putInt("productId", productId)
                }
                findNavController().navigate(
                    R.id.action_homeFragment_to_productDetailFragment,
                    bundle
                )
            }

            else -> {
                Timber.d("Product clicked")
                showSnackBarShort("Product clicked", binding.root)
//                findNavController().navigate(R.id.action_homeFragment_to_productDetailFragment)
            }
        }
    }

    /*========================================================================
          FUNCTIONS
     =========================================================================*/
}
