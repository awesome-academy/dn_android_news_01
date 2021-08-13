package com.edu.news.data.source.local

import com.edu.news.data.model.Category
import com.edu.news.data.source.CategoryDataSource
import com.edu.news.data.source.local.database.LocalDataBaseHelper
import java.lang.Exception

class CategoryLocalDataSource(private val database: LocalDataBaseHelper) : CategoryDataSource.Local {

    override fun getCategoriesFromLocal(listener: OnFetchDataListener<MutableList<Category>>) {
        database.getCategory(listener)
    }

    override fun saveCategoriesToLocal(listener: OnPostDataListener<Exception>, categoryNames: MutableList<String>) {
        database.saveCategories(listener, categoryNames)
    }

    companion object {
        private var instance: CategoryLocalDataSource? = null

        fun getInstance(database: LocalDataBaseHelper) = synchronized(this) {
            instance ?: CategoryLocalDataSource(database).also { instance = it }
        }
    }
}
