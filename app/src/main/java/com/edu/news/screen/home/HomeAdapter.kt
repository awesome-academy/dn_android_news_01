package com.edu.news.screen.home

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.edu.news.screen.home.category.ListNewsFragment

class HomeAdapter(fragment: Fragment, private val categories: Array<String>) : FragmentStateAdapter(fragment) {

    override fun getItemCount() = categories.size

    override fun createFragment(position: Int) = ListNewsFragment.newInstance()
}
