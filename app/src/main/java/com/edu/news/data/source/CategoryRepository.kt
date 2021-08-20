package com.edu.news.data.source

import com.edu.news.data.model.Category
import com.edu.news.data.model.CategoryType
import com.edu.news.data.source.local.OnPostDataListener
import java.lang.Exception

class CategoryRepository private constructor(
    private val local: CategoryDataSource.Local) {

    fun getCategories(listener: OnFetchDataListener<MutableList<Category>>) {
        local.getCategoriesFromLocal(listener)
    }

    fun saveCategories(listener: OnPostDataListener<Exception>, categoryTypes: MutableList<CategoryType>) {
        local.saveCategoriesToLocal(listener, categoryTypes)
    }

    companion object {
        private var instance: CategoryRepository? = null

        fun getInstance(local: CategoryDataSource.Local) = synchronized(this) {
            instance ?: CategoryRepository(local).also { instance = it }
        }
    }
}
