package com.edu.news.screen.home

import android.view.View
import android.widget.Toast
import com.edu.news.data.model.CategoryType
import com.edu.news.data.source.CategoryRepository
import com.edu.news.data.source.local.CategoryLocalDataSource
import com.edu.news.data.source.local.database.LocalDataBaseHelper
import com.edu.news.screen.articles.ArticlesFragment
import com.edu.news.utils.base.BaseFragment
import com.google.android.material.tabs.TabLayoutMediator
import com.sun.news.R
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeFragment : BaseFragment(), HomeContract.View {

    private lateinit var homePresenter: HomePresenter
    private val homeAdapter by lazy { HomeAdapter(this) }
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
            getCategories()
        }
    }

    override fun onGetCategoriesSuccess(result: MutableList<CategoryType>?) {
        if (result?.isNullOrEmpty() == true) {
            result.addAll(CategoryType.values().toMutableList())
            homePresenter.saveCategories(result)
        }
        homeAdapter.updateData(result.map { categoryType ->
            ArticlesFragment.newInstance(categoryType)
        }.toMutableList())
        TabLayoutMediator(tabLayoutCategoryHome, viewPagerCategoryHome) { tab, position ->
            tab.text = result[position].nameType
        }.attach()
    }

    override fun onError(exception: Exception?) {
        Toast.makeText(view?.context, exception.toString(), Toast.LENGTH_SHORT).show()
    }

    companion object {
        fun newInstance() = HomeFragment()
    }
}
