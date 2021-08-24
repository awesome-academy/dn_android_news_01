package com.edu.news.screen.nav

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.edu.news.screen.favorite.FavoriteFragment
import com.edu.news.screen.follows.FollowsFragment
import com.edu.news.screen.home.HomeFragment
import com.edu.news.utils.MenuItem
import com.edu.news.utils.base.BaseFragment
import com.sun.news.R
import kotlinx.android.synthetic.main.fragment_bottom_navigation.*
import kotlinx.android.synthetic.main.fragment_bottom_navigation.view.*

class BottomNavFragment : BaseFragment() {

    override fun getLayoutResourceId() = R.layout.fragment_bottom_navigation

    override fun initView(view: View) {
        val fragments = listOf<Fragment>(
            HomeFragment.newInstance(),
            FavoriteFragment.newInstance(),
            FollowsFragment.newInstance()
        )
        view.viewPagerBottomNavigation.apply {
            isUserInputEnabled = false
            adapter = BottomNavAdapter(this@BottomNavFragment, fragments)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registerEventNavigation()
    }

    override fun initData() {
    }

    private fun registerEventNavigation() {
        bottomNavigationView?.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.itemHome -> {
                    viewPagerBottomNavigation.currentItem = MenuItem.HOME_ITEM.ordinal
                    true
                }
                R.id.itemFavorite -> {
                    viewPagerBottomNavigation.currentItem = MenuItem.FAVORITE_ITEM.ordinal
                    true
                }
                R.id.itemFollows -> {
                    viewPagerBottomNavigation.currentItem = MenuItem.FOLLOWS_ITEM.ordinal
                    true
                }
                else -> true
            }
        }
    }

    companion object {
        fun newInstance() = BottomNavFragment()
    }
}
