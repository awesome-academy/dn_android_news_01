package com.edu.news.data.source

import com.edu.news.data.model.Article
import com.edu.news.data.model.CategoryType

interface ArticleDataSource {

    interface Remote {
        fun getArticles(
            listener: OnFetchDataListener<MutableList<Article>>,
            categoryType: CategoryType,
            pageNumber: Int
        )
    }
}
