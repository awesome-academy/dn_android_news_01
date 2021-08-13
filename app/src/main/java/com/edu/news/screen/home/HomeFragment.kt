package com.edu.news.screen.home

import android.view.View
import android.widget.Toast
import com.edu.news.data.source.CategoryRepository
import com.edu.news.data.source.local.CategoryLocalDataSource
import com.edu.news.data.source.local.database.LocalDataBaseHelper
import com.edu.news.utils.base.BaseFragment
import com.google.android.material.tabs.TabLayoutMediator
import com.sun.news.R
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeFragment : BaseFragment(), HomeContract.View {

    private lateinit var homePresenter: HomePresenter
    private val homeAdapter by lazy { HomeAdapter(this) }
    private val categories = mutableListOf(
        "Following",
        "Top-Headline",
        "Sport",
        "Technology",
        "Business",
        "Entertainment",
        "Health"
    )

    override fun getLayoutResourceId() = R.layout.fragment_home

    override fun initView(view: View) {
        view.viewPagerCategoryHome.adapter = homeAdapter
    }

    override fun initData() {
        activity?.let {
            HomePresenter(
                CategoryRepository.getInstance(
                    CategoryLocalDataSource.getInstance(
                         LocalDataBaseHelper(it.applicationContext)
                    )
                )
            )
        }?.run {
            homePresenter = this
            setView(this@HomeFragment)
            onStart()
        }
    }

    override fun onGetCategoriesSuccess(result: MutableList<String>?) {
        if (result?.isNullOrEmpty() == true) {
            result.addAll(categories)
            homePresenter.saveCategories(result)
        }
        homeAdapter.updateData(result)
        TabLayoutMediator(tabLayoutCategoryHome, viewPagerCategoryHome) { tab, position ->
            tab.text = result[position]
        }.attach()
    }

    override fun onError(exception: Exception?) {
        Toast.makeText(view?.context, exception.toString(), Toast.LENGTH_SHORT).show()
    }

    companion object {
        fun newInstance() = HomeFragment()
    }
}
