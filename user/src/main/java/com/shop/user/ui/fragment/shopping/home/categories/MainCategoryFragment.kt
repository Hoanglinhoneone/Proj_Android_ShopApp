package com.shop.user.ui.fragment.shopping.home.categories

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
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
import com.shop.user.viewmodel.home.State
import kotlinx.coroutines.launch
import timber.log.Timber

class MainCategoryFragment :
    BaseFragment<FragmentMainCategoryBinding>(FragmentMainCategoryBinding::inflate),
    OnItemClickListener {
    /*========================================================================
             VARIABLES
    =========================================================================*/
    private val viewModel by viewModels<MainCategoryViewModel>()
    private val adapter: MainCategoryAdapter by lazy {
        MainCategoryAdapter(mutableListOf(), this)
    }

    /*========================================================================
     OVERRIDDEN METHODS
    =========================================================================*/
    override fun initView() {
        Timber.i("initView")
        binding.recyclerview.setHasFixedSize(true)
        binding.recyclerview.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }

    override fun observeData() {
        Timber.i("observeData")
        viewModel.dataList.observe(viewLifecycleOwner) {
            adapter.updateData(it)
            binding.recyclerview.adapter = adapter
        }
        lifecycleScope.launch {
            val newList = mutableListOf<Item>()
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.uiState.collect { uiState ->
                    when (uiState) {
                        is State.Loading -> {
                            showLoading(true)
                        }

                        is State.Success -> {
                            Timber.d("Loading success")
                            val banners = uiState.banners
                            val bestSellers = uiState.bestSellers
                            val products = uiState.products
                            newList.add(Item(Item.LIST_BANNER, banners = banners))
                            newList.add(Item(Item.BEST_SELLER, bestSellers = bestSellers))
                            newList.add(Item(Item.LIST_PRODUCT, products = products))
                            updateDataList(newList)
                            showLoading(false)
                            Timber.d("banner size = ${banners.size}, best seller size = ${bestSellers.size}, product size = ${products.size}")
                        }

                        is State.Error -> {
                            showLoading(false)
                            Timber.d("Error: ${uiState.message}")
                        }
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
                val bundle = Bundle().apply {
                    putInt("bannerId", position)
                }
                findNavController().navigate(
                    R.id.action_homeFragment_to_productDetailFragment,
                    bundle
                )
                showSnackBarShort("Banner clicked", binding.root)
            }

            Item.BEST_SELLER -> {
                Timber.d("Best seller clicked")
                Toast.makeText(context, "Best seller clicked", Toast.LENGTH_SHORT).show()
                showSnackBarShort("Best seller clicked", binding.root)
                val bundle = Bundle().apply {
                    putInt("bestsellerId", position)
                }
                findNavController().navigate(
                    R.id.action_homeFragment_to_productDetailFragment,
                    bundle
                )
            }

            else -> {
                Timber.d("Product clicked")
                val bundle = Bundle().apply {
                    putInt("productId", position)
                }
                showSnackBarShort("Product clicked", binding.root)
                findNavController().navigate(
                    R.id.action_homeFragment_to_productDetailFragment,
                    bundle
                )
            }
        }
    }

    /*========================================================================
          FUNCTIONS
     =========================================================================*/
    private fun updateDataList(newList: MutableList<Item>) {
        viewModel.dataList.value = newList
    }

    private fun showLoading(show: Boolean) {
        if (show) {
            binding.loading.visibility = android.view.View.VISIBLE
        } else {
            binding.loading.visibility = android.view.View.GONE
        }
    }
}
