package com.edu.news.screen.home

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.edu.news.screen.home.category.ListNewsFragment

class HomeAdapter internal constructor(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val categoryNames = mutableListOf<String>()

    override fun getItemCount() = categoryNames.size

    override fun createFragment(position: Int) = ListNewsFragment.newInstance()

    fun updateData(categoryNames: MutableList<String>?) {
        categoryNames?.let {
            this.categoryNames.clear()
            this.categoryNames.addAll(it)
            notifyDataSetChanged()
        }
    }
}
