package com.edu.news.screen.main

import androidx.fragment.app.Fragment
import com.edu.news.screen.favorite.FavoriteFragment
import com.edu.news.screen.follows.FollowsFragment
import com.edu.news.screen.home.HomeFragment
import com.edu.news.screen.nav.BottomNavAdapter
import com.edu.news.utils.ItemBottomNav
import com.edu.news.utils.base.BaseActivity
import com.sun.news.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun getLayoutResourceId() = R.layout.activity_main

    override fun initView() {
        val fragments = listOf<Fragment>(
            HomeFragment.newInstance(),
            FavoriteFragment.newInstance(),
            FollowsFragment.newInstance()
        )
        viewPagerBottomNavigation.adapter = BottomNavAdapter(this, fragments)
        registerEventNavigation()
    }

    private fun registerEventNavigation() {
        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.itemHome -> {
                    viewPagerBottomNavigation.currentItem = ItemBottomNav.HOME_ITEM.ordinal
                    true
                }
                R.id.itemFavorite -> {
                    viewPagerBottomNavigation.currentItem = ItemBottomNav.FAVORITE_ITEM.ordinal
                    true
                }
                R.id.itemFollows -> {
                    viewPagerBottomNavigation.currentItem = ItemBottomNav.FOLLOWS_ITEM.ordinal
                    true
                }
                else -> true
            }
        }
    }
}
