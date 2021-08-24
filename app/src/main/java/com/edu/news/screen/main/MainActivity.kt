package com.edu.news.screen.main

import com.edu.news.screen.nav.BottomNavFragment
import com.edu.news.utils.base.BaseActivity
import com.sun.news.R

class MainActivity : BaseActivity() {

    override fun getLayoutResourceId() = R.layout.activity_main

    override fun initView() {
        supportFragmentManager
            .beginTransaction()
            .addToBackStack(BottomNavFragment::javaClass.name)
            .replace(R.id.frameLayoutContainer, BottomNavFragment.newInstance())
            .commit()
    }
}
