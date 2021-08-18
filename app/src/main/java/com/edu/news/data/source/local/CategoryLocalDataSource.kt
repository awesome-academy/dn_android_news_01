package com.edu.news.data.source.local

import com.edu.news.data.model.Category
import com.edu.news.data.model.CategoryType
import com.edu.news.data.source.CategoryDataSource
import com.edu.news.data.source.OnFetchDataListener
import com.edu.news.data.source.local.database.LocalDataBaseHelper
import java.lang.Exception

class CategoryLocalDataSource(private val database: LocalDataBaseHelper) : CategoryDataSource.Local {

    override fun getCategoriesFromLocal(listener: OnFetchDataListener<MutableList<Category>>) {
        database.getCategory(listener)
    }

    override fun saveCategoriesToLocal(listener: OnPostDataListener<Exception>, categoryTypes: MutableList<CategoryType>) {
        database.saveCategories(listener, categoryTypes)
    }

    companion object {
        private var instance: CategoryLocalDataSource? = null

        fun getInstance(database: LocalDataBaseHelper) = synchronized(this) {
            instance ?: CategoryLocalDataSource(database).also { instance = it }
        }
    }
}
