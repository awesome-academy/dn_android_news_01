package com.edu.news.screen.home

import com.edu.news.utils.base.BasePresenter

class HomeContract {

    interface View {
        fun onGetCategoriesSuccess(result: MutableList<String>?)
        fun onError(exception: Exception?)
    }

    interface Presenter : BasePresenter<View> {
        fun getCategories()
        fun saveCategories(categoryNames: MutableList<String>)
    }
}
