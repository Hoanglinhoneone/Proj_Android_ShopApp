package com.example.user_app.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
abstract class BaseFragment : Fragment() {

    private lateinit var view: View
    private var mProgressBar: ProgressBar? = null

    abstract fun getLayoutId(): Int
    abstract fun setUpView(view: View)
//    abstract fun setUpToolbar()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        view = inflater.inflate(getLayoutId(), container, false)
        setUpView(view)
        return view
    }

    fun showProgressBar() {
        mProgressBar?.visibility = View.VISIBLE
    }

    fun hideProgressBar() {
        mProgressBar?.visibility = View.GONE
    }

    fun setUpProgressBar(progressBar: ProgressBar) {
        mProgressBar = progressBar
    }
}