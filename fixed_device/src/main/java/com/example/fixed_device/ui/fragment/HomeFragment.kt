package com.example.fixed_device.ui.fragment

import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.fixed_device.R
import com.example.fixed_device.databinding.FragmentHomeBinding
import timber.log.Timber

class HomeFragment : BaseFragment() {
    private lateinit var binding: FragmentHomeBinding

    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun setUpView(view: View) {
        binding = FragmentHomeBinding.bind(view)
        Timber.e("setUpView")
//        setUpToolbar()
    }

//    @RequiresApi(Build.VERSION_CODES.M)
//    override fun setUpToolbar() {
//        val toolbar = binding.tbCommon.toolbar
//        (activity as AppCompatActivity).apply {
//            setSupportActionBar(toolbar)
//            supportActionBar?.title = "Home"
//            supportActionBar.apply {
//            }
//            supportActionBar?.setDisplayHomeAsUpEnabled(true)
//            supportActionBar?.setDisplayShowHomeEnabled(true)
//        }
//        toolbar.setTitleTextColor(
//            resources.getColor(
//                R.color.white,
//                null
//            )
//        )
//        toolbar.setNavigationOnClickListener {
//            activity?.onBackPressedDispatcher?.onBackPressed()
//        }
//    }
}