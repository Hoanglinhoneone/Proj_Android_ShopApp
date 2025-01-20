package com.shop.user.ui.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.shop.user.R
import com.shop.user.databinding.ActivityShoppingBinding
import com.shop.user.ui.adapter.Viewpager2Adapter
import com.shop.user.ui.fragment.shopping.SearchFragment
import com.shop.user.ui.fragment.shopping.cart.CartFragment
import com.shop.user.ui.fragment.shopping.home.HomeFragment
import com.shop.user.ui.fragment.shopping.setting.ProfileFragment
import timber.log.Timber

class ShoppingActivity : AppCompatActivity() {
    /*========================================================================
      VARIABLES
    =========================================================================*/
    private val binding by lazy {
        ActivityShoppingBinding.inflate(layoutInflater)
    }

    /*========================================================================
        OVERRIDDEN METHODS
    =========================================================================*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
//
        Timber.i("Shopping Activity created")
        setContentView(binding.root)

        val listFragment =
            listOf(HomeFragment(), SearchFragment(), CartFragment(), ProfileFragment())
        val adapter = Viewpager2Adapter(this, listFragment)
        binding.contentPage.adapter = adapter

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.homeFragment -> {
                    binding.contentPage.currentItem = 0
                    setUpToolbar("Home", false)
                }

                R.id.searchFragment -> {
                    binding.contentPage.currentItem = 1
                    setUpToolbar("Search", true)
                }

                R.id.cartFragment -> {
                    binding.contentPage.currentItem = 2
                    setUpToolbar("Cart", true)
                }

                R.id.profileFragment -> {
                    binding.contentPage.currentItem = 3
                    setUpToolbar("Profile", false)
                }
            }
            true
        }

        binding.contentPage.apply {
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    when (position) {
                        0 -> binding.bottomNavigation.selectedItemId = R.id.homeFragment
                        1 -> binding.bottomNavigation.selectedItemId = R.id.searchFragment
                        2 -> binding.bottomNavigation.selectedItemId = R.id.cartFragment
                        3 -> binding.bottomNavigation.selectedItemId = R.id.profileFragment
                    }
                }
            })
            isUserInputEnabled = false
        }
    }

    /*========================================================================
        FUNCTIONS
    =========================================================================*/
    private fun setUpToolbar(titlePage: String, isVisible: Boolean) {
        binding.titlePage.apply {
            title = titlePage
            visibility = if (isVisible) View.VISIBLE else View.GONE
        }
    }
}