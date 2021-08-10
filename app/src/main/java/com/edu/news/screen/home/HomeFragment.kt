package com.edu.news.screen.home

import android.view.View
import com.edu.news.utils.base.BaseFragment
import com.sun.news.R

class HomeFragment : BaseFragment() {

    override fun getLayoutResourceId() = R.layout.fragment_home

    override fun initView(view: View) {
    }

    override fun initData() {
    }

    companion object {
        fun newInstance() = HomeFragment()
    }
}
