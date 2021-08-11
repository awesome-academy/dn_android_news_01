package com.edu.news.screen.home

import android.view.View
import com.edu.news.utils.base.BaseFragment
import com.google.android.material.tabs.TabLayoutMediator
import com.sun.news.R
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeFragment : BaseFragment() {

    override fun getLayoutResourceId() = R.layout.fragment_home

    override fun initView(view: View) {
        val listCategory = view.resources.getStringArray(R.array.categories)
        view.viewPagerCategoryHome.adapter = HomeAdapter(this@HomeFragment, listCategory)
        TabLayoutMediator(view.tabLayoutCategoryHome, view.viewPagerCategoryHome) { tab, position ->
            tab.text = listCategory[position]
        }.attach()
    }

    override fun initData() {
    }

    companion object {
        fun newInstance() = HomeFragment()
    }
}
