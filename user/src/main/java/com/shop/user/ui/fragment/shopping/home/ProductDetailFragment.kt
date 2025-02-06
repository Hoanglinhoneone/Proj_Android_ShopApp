package com.shop.user.ui.fragment.shopping.home

import android.annotation.SuppressLint
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.shop.user.databinding.FragmentProductDetailBinding
import com.shop.user.ui.fragment.BaseFragment
import com.shop.user.viewmodel.home.ProductDetailViewModel
import timber.log.Timber

class ProductDetailFragment : BaseFragment<FragmentProductDetailBinding>(
    FragmentProductDetailBinding::inflate
) {
    /*========================================================================
      VARIABLES
    =========================================================================*/
    private val viewModel by viewModels<ProductDetailViewModel>()
//    private val args: ProductDetailFragmentArgs by navArgs()

    /*========================================================================
        OVERRIDDEN METHODS
    =========================================================================*/
    override fun initView() {
        Timber.i("initView")
        binding.apply {
            close.setOnClickListener {
                Timber.i("close clicked")
                findNavController().popBackStack()
            }
        }
        val productId = arguments?.getInt("productId")
        Timber.d("productId: $productId")
        binding.apply {
            priceProduct.text = "$productId"
        }
    }

    @SuppressLint("SetTextI18n")
    override fun observeData() {
        Timber.i("observeData")
        viewModel.product.observe(viewLifecycleOwner) { product ->
            Timber.d("product selected: $product")
            binding.apply {
                nameProduct.text = product.toString()
            }
        }
    }
}