package com.edu.news.screen.home

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class HomeAdapter internal constructor(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val fragments = mutableListOf<Fragment>()

    override fun getItemCount() = fragments.size

    override fun createFragment(position: Int) = fragments[position]

    fun updateData(data: MutableList<Fragment>?) {
        data?.let {
            fragments.clear()
            fragments.addAll(it)
            notifyDataSetChanged()
        }
    }
}
