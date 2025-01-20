package com.shop.user.ui.fragment.shopping.home.categories

import androidx.recyclerview.widget.GridLayoutManager
import com.shop.user.R
import com.shop.user.data.model.Product
import com.shop.user.databinding.FragmentChairBinding
import com.shop.user.ui.adapter.home.ChairAdapter
import com.shop.user.ui.fragment.BaseFragment

class ChairFragment : BaseFragment<FragmentChairBinding>(FragmentChairBinding::inflate) {
    /*========================================================================
      VARIABLES
    =========================================================================*/
    private lateinit var listChair: ArrayList<Product>
    private lateinit var adapter: ChairAdapter

    /*========================================================================
      OVERRIDDEN METHODS
    =========================================================================*/
    override fun initView() {
        listChair = ArrayList()
        listChair.add(Product(R.drawable.women_clothing, "Up to 20% off"))
        listChair.add(Product(R.drawable.levis_clothing, "Up to 20% off"))
        listChair.add(Product(R.drawable.levis_clothing, "Up to 20% off"))
        listChair.add(Product(R.drawable.levis_clothing, "Up to 20% off"))
        listChair.add(Product(R.drawable.levis_clothing, "Up to 20% off"))
        listChair.add(Product(R.drawable.levis_clothing, "Up to 20% off"))
        listChair.add(Product(R.drawable.levis_clothing, "Up to 20% off"))
        listChair.add(Product(R.drawable.levis_clothing, "Up to 20% off"))
        listChair.add(Product(R.drawable.levis_clothing, "Up to 20% off"))
        listChair.add(Product(R.drawable.levis_clothing, "Up to 20% off"))
        listChair.add(Product(R.drawable.levis_clothing, "Up to 20% off"))
        adapter = ChairAdapter(listChair)
        binding.listChair.setHasFixedSize(true)
        binding.listChair.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.listChair.adapter = adapter
    }

    override fun observeData() {

    }

}