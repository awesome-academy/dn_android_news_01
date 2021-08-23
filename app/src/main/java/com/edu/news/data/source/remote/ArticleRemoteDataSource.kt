package com.edu.news.data.source.remote

import com.edu.news.data.model.Article
import com.edu.news.data.model.CategoryType
import com.edu.news.data.source.ArticleDataSource
import com.edu.news.data.source.OnFetchDataListener
import com.edu.news.data.source.remote.fetchData.GetDataFromUrl
import com.edu.news.utils.Constant

class ArticleRemoteDataSource : ArticleDataSource.Remote {

    private val apiService = GetDataFromUrl()
    private val urlGetArticles =
        Constant.BASE_URL + Constant.BASE_COUNTRY + Constant.BASE_API_KEY

    override fun getArticles(
        listener: OnFetchDataListener<MutableList<Article>>,
        categoryType: CategoryType,
        pageNumber: Int
    ) {
        val key = if (categoryType == CategoryType.TOP_HEADLINE) Constant.BASE_PAGE_PARAM
        else Constant.BASE_CATEGORY_PARAM + categoryType.nameType + Constant.BASE_PAGE_PARAM
        apiService.callAPI(listener, urlGetArticles, key + pageNumber)
    }

    companion object {
        private var instance: ArticleRemoteDataSource? = null

        fun getInstance() = synchronized(this) {
            instance ?: ArticleRemoteDataSource().also { instance = it }
        }
    }
}
