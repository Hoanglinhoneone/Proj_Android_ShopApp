package com.shop.user.ui.fragment.shopping.home

import com.google.android.material.tabs.TabLayoutMediator
import com.shop.user.databinding.FragmentHomeBinding
import com.shop.user.ui.adapter.home.HomeViewPagerAdapter
import com.shop.user.ui.fragment.BaseFragment
import com.shop.user.ui.fragment.shopping.home.categories.AccessoryFragment
import com.shop.user.ui.fragment.shopping.home.categories.ChairFragment
import com.shop.user.ui.fragment.shopping.home.categories.CupBoardFragment
import com.shop.user.ui.fragment.shopping.home.categories.FurnitureFragment
import com.shop.user.ui.fragment.shopping.home.categories.MainCategoryFragment
import com.shop.user.ui.fragment.shopping.home.categories.TableFragment

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {
    /*========================================================================
        VARIABLES
    =========================================================================*/

    /*========================================================================
        OVERRIDDEN METHODS
    =========================================================================*/
    override fun initView() {
        val categoryFragments = listOf(
            MainCategoryFragment(),
            ChairFragment(),
            CupBoardFragment(),
            TableFragment(),
            AccessoryFragment(),
            FurnitureFragment()
        )
        val viewpager2Adapter =
            HomeViewPagerAdapter(categoryFragments, childFragmentManager, lifecycle)
        binding.viewPager2.apply {
            adapter = viewpager2Adapter
            isUserInputEnabled = false
        }
        TabLayoutMediator(binding.tabLayout, binding.viewPager2) { tab, position ->
            when (position) {
                0 -> tab.text = "Main"
                1 -> tab.text = "Chair"
                2 -> tab.text = "Cupboard"
                3 -> tab.text = "Table"
                4 -> tab.text = "Accessory"
                5 -> tab.text = "Furniture"
            }
        }.attach()
    }

    override fun observeData() {
    }

    /*========================================================================
        FUNCTIONS
    =========================================================================*/
}