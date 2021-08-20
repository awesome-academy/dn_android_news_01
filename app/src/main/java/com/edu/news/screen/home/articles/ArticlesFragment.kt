package com.edu.news.screen.home.articles

import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import com.edu.news.data.model.Article
import com.edu.news.data.model.CategoryType
import com.edu.news.data.source.ArticleRepository
import com.edu.news.data.source.remote.ArticleRemoteDataSource
import com.edu.news.utils.base.BaseFragment
import com.sun.news.R
import kotlinx.android.synthetic.main.fragment_articles.view.*

class ArticlesFragment : BaseFragment(), ArticlesContract.View {

    private  val articlesAdapter by lazy { ArticlesAdapter() }

    override fun getLayoutResourceId() = R.layout.fragment_articles

    override fun initView(view: View) {
        view.recyclerViewArticles.adapter = articlesAdapter
    }

    override fun initData() {
        val categoryType = arguments?.getSerializable(ARGUMENT_CATEGORY_NAME) as CategoryType
        ArticlePresenter(
            ArticleRepository.getInstance(
                ArticleRemoteDataSource.getInstance()
            )
        ).run {
            setView(this@ArticlesFragment)
            getArticles(categoryType)
        }
    }

    override fun onGetArticlesSuccess(articles: MutableList<Article>) {
        if (!articles.isNullOrEmpty()) {
            articlesAdapter.updateData(articles)
        }
    }

    override fun onError(exception: Exception?) {
        Toast.makeText(view?.context, exception.toString(), Toast.LENGTH_SHORT).show()
    }

    companion object {
        private const val ARGUMENT_CATEGORY_NAME = "ARGUMENT_CATEGORY_NAME"

        fun newInstance(categoryType: CategoryType) = ArticlesFragment().apply {
            arguments = bundleOf(ARGUMENT_CATEGORY_NAME to categoryType)
        }
    }
}
