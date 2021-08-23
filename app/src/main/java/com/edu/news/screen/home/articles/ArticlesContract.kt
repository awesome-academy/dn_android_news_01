package com.edu.news.screen.home.articles

import com.edu.news.data.model.Article
import com.edu.news.data.model.CategoryType
import com.edu.news.utils.base.BasePresenter

class ArticlesContract {

    interface View {
        fun onGetArticlesSuccess(articles: MutableList<Article>)
        fun onError(exception: Exception?)
    }

    interface Presenter : BasePresenter<View> {
        fun getArticles(categoryType: CategoryType, pageNumber: Int)
    }
}
