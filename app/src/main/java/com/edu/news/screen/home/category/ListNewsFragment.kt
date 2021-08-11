package com.edu.news.screen.home.category

import android.view.View
import com.edu.news.utils.base.BaseFragment
import com.sun.news.R

class ListNewsFragment : BaseFragment() {

    override fun getLayoutResourceId() = R.layout.fragment_list_news

    override fun initView(view: View) {
    }

    override fun initData() {
    }

    companion object {
        fun newInstance() = ListNewsFragment()
    }
}
