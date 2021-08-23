package com.edu.news.screen.main

import androidx.fragment.app.Fragment
import com.edu.news.screen.favorite.FavoriteFragment
import com.edu.news.screen.follows.FollowsFragment
import com.edu.news.screen.home.HomeFragment
import com.edu.news.screen.nav.BottomNavAdapter
import com.edu.news.utils.MenuItem
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
        viewPagerBottomNavigation.apply {
            isUserInputEnabled = false
            adapter = BottomNavAdapter(this@MainActivity, fragments)
        }
        registerEventNavigation()
    }

    private fun registerEventNavigation() {
        bottomNavigationView.setOnNavigationItemSelectedListener {
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
}
