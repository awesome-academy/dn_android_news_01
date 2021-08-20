package com.edu.news.screen.home.articles

import com.edu.news.data.model.Article
import com.edu.news.data.model.CategoryType
import com.edu.news.data.source.ArticleRepository
import com.edu.news.data.source.OnFetchDataListener

class ArticlePresenter internal constructor(private val repository: ArticleRepository) :
    ArticlesContract.Presenter {

    private var view: ArticlesContract.View? = null

    override fun setView(view: ArticlesContract.View?) {
        this.view = view
    }

    override fun getArticles(categoryType: CategoryType) {
        repository.getArticles(object : OnFetchDataListener<MutableList<Article>> {
            override fun onSuccess(data: MutableList<Article>) {
                view?.onGetArticlesSuccess(data)
            }

            override fun onError(exception: Exception) {
                view?.onError(exception)
            }
        }, categoryType)
    }
}
