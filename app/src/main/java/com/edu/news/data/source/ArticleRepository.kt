package com.edu.news.data.source

import com.edu.news.data.model.Article
import com.edu.news.data.model.CategoryType

class ArticleRepository private constructor(private val remote: ArticleDataSource.Remote) {

    fun getArticles(
        listener: OnFetchDataListener<MutableList<Article>>,
        categoryType: CategoryType,
        pageNumber: Int
    ) {
        remote.getArticles(listener, categoryType, pageNumber)
    }

    companion object {
        private var instance: ArticleRepository? = null

        fun getInstance(remote: ArticleDataSource.Remote) = synchronized(this) {
            instance ?: ArticleRepository(remote).also { instance = it }
        }
    }
}
