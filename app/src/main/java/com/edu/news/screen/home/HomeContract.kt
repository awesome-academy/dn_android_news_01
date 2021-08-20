package com.edu.news.screen.home

import com.edu.news.data.model.CategoryType
import com.edu.news.utils.base.BasePresenter

class HomeContract {

    interface View {
        fun onGetCategoriesSuccess(result: MutableList<CategoryType>?)
        fun onError(exception: Exception?)
    }

    interface Presenter : BasePresenter<View> {
        fun getCategories()
        fun saveCategories(categoryTypes: MutableList<CategoryType>)
    }
}
