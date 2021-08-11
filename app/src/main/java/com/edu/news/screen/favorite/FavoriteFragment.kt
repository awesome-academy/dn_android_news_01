package com.edu.news.screen.favorite

import android.view.View
import com.edu.news.utils.base.BaseFragment
import com.sun.news.R

class FavoriteFragment : BaseFragment() {

    override fun getLayoutResourceId() = R.layout.fragment_favorite

    override fun initView(view: View) {
    }

    override fun initData() {
    }

    companion object {
        fun newInstance() = FavoriteFragment()
    }
}
