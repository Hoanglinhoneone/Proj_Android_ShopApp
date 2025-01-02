package com.example.user_app.ui.fragment.shopping

import android.view.View
import androidx.lifecycle.whenCreated
import com.example.user_app.R
import com.example.user_app.databinding.FragmentHomeBinding
import com.example.user_app.ui.adapter.HomeViewPagerAdapter
import com.example.user_app.ui.fragment.BaseFragment
import com.example.user_app.ui.fragment.categories.AccessoryFragment
import com.example.user_app.ui.fragment.categories.ChairFragment
import com.example.user_app.ui.fragment.categories.CupBoardFragment
import com.example.user_app.ui.fragment.categories.FurnitureFragment
import com.example.user_app.ui.fragment.categories.MainCategoryFragment
import com.example.user_app.ui.fragment.categories.TableFragment
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : BaseFragment() {
    private lateinit var binding: FragmentHomeBinding
    override fun getLayoutId(): Int {
        return  R.layout.fragment_home
    }

    override fun setUpView(view: View) {
        binding = FragmentHomeBinding.bind(view)

        val categoryFragments = arrayListOf(
            MainCategoryFragment(),
            ChairFragment(),
            CupBoardFragment(),
            TableFragment(),
            AccessoryFragment(),
            FurnitureFragment()
        )
        val viewpager2Adapter =
            HomeViewPagerAdapter(categoryFragments, childFragmentManager, lifecycle)
        binding.viewPager2.adapter = viewpager2Adapter
        TabLayoutMediator(binding.tabLayout, binding.viewPager2) { tab, position ->
            when(position) {
                0 -> tab.text = "Main"
                1 -> tab.text = "Chair"
                2 -> tab.text = "Cupboard"
                3 -> tab.text = "Table"
                4 -> tab.text = "Accessory"
                5 -> tab.text = "Furniture"
            }
        }.attach()
    }
}