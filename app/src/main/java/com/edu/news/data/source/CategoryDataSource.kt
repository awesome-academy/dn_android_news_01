package com.edu.news.data.source

import com.edu.news.data.model.Category
import com.edu.news.data.source.local.OnFetchDataListener
import com.edu.news.data.source.local.OnPostDataListener
import java.lang.Exception

interface CategoryDataSource {

    interface Local {
        fun getCategoriesFromLocal(listener: OnFetchDataListener<MutableList<Category>>)
        fun saveCategoriesToLocal(listener: OnPostDataListener<Exception>, categoryNames: MutableList<String>)
    }
}
