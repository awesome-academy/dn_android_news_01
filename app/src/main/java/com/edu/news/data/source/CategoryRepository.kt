package com.edu.news.data.source

import com.edu.news.data.model.Category
import com.edu.news.data.source.local.OnFetchDataListener
import com.edu.news.data.source.local.OnPostDataListener
import java.lang.Exception

class CategoryRepository private constructor(
    private val local: CategoryDataSource.Local) {

    fun getCategories(listener: OnFetchDataListener<MutableList<Category>>) {
        local.getCategoriesFromLocal(listener)
    }

    fun saveCategories(listener: OnPostDataListener<Exception>, categoryNames: MutableList<String>) {
        local.saveCategoriesToLocal(listener, categoryNames)
    }

    companion object {
        private var instance: CategoryRepository? = null

        fun getInstance(local: CategoryDataSource.Local) = synchronized(this) {
            instance ?: CategoryRepository(local).also { instance = it }
        }
    }
}
