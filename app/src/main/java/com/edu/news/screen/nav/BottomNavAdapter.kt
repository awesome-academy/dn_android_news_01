package com.edu.news.screen.nav

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class BottomNavAdapter(fragment: Fragment, private val fragments: List<Fragment>)
    : FragmentStateAdapter(fragment) {

    override fun getItemCount() = fragments.size

    override fun createFragment(position: Int) = fragments[position]
}
