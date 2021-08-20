package com.edu.news.data.source

import com.edu.news.data.model.Category
import com.edu.news.data.model.CategoryType
import com.edu.news.data.source.local.OnPostDataListener

interface CategoryDataSource {

    interface Local {
        fun getCategoriesFromLocal(listener: OnFetchDataListener<MutableList<Category>>)
        fun saveCategoriesToLocal(
            listener: OnPostDataListener<Exception>,
            categoryTypes: MutableList<CategoryType>
        )
    }
}
