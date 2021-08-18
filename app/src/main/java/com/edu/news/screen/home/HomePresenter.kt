package com.edu.news.screen.home

import com.edu.news.data.model.Category
import com.edu.news.data.source.CategoryRepository
import com.edu.news.data.source.local.OnFetchDataListener
import com.edu.news.data.source.local.OnPostDataListener
import java.lang.Exception

class HomePresenter internal constructor(private val repository: CategoryRepository)
    : HomeContract.Presenter {

    private var view: HomeContract.View? = null

    override fun onStart() {
        getCategories()
    }

    override fun setView(view: HomeContract.View?) {
        this.view = view
    }

    override fun getCategories() {
        repository.getCategories(object : OnFetchDataListener<MutableList<Category>> {
            override fun onSuccess(data: MutableList<Category>) {
                view?.onGetCategoriesSuccess(data.map { category -> category.name }.toMutableList())
            }

            override fun onError(exception: Exception) {
                view?.onError(exception)
            }
        })
    }

    override fun saveCategories(categoryNames: MutableList<String>) {
        repository.saveCategories(object : OnPostDataListener<Exception> {
            override fun onError(exception: Exception) {
                view?.onError(exception)
            } },
            categoryNames)
    }
}
