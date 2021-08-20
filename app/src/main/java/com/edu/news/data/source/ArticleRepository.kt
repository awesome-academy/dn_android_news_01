package com.edu.news.data.source

import com.edu.news.data.model.Article
import com.edu.news.data.model.CategoryType

class ArticleRepository private constructor(private val remote: ArticleDataSource.Remote) {

    fun getArticles(listener: OnFetchDataListener<MutableList<Article>>, categoryType: CategoryType) {
        remote.getArticles(listener, categoryType)
    }

    companion object {
        private var instance: ArticleRepository? = null

        fun getInstance(remote: ArticleDataSource.Remote) = synchronized(this) {
            instance ?: ArticleRepository(remote).also { instance = it }
        }
    }
}
