package com.edu.news.screen.home.articles

import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.edu.news.data.model.Article
import com.edu.news.data.model.CategoryType
import com.edu.news.data.source.ArticleRepository
import com.edu.news.data.source.remote.ArticleRemoteDataSource
import com.edu.news.utils.base.BaseFragment
import com.sun.news.R
import kotlinx.android.synthetic.main.fragment_articles.view.*

class ArticlesFragment : BaseFragment(), ArticlesContract.View {

    private lateinit var articlePresenter: ArticlePresenter
    private lateinit var categoryType: CategoryType
    private val articlesAdapter by lazy { ArticlesAdapter() }
    private var pageNumber = 1

    override fun getLayoutResourceId() = R.layout.fragment_articles

    override fun initView(view: View) {
        val dividerItemDecoration =
            DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        with(view) {
            recyclerViewArticles.apply {
                adapter = articlesAdapter
                addItemDecoration(dividerItemDecoration)
            }
            refreshArticles.run {
                setOnRefreshListener {
                    pageNumber = 1
                    articlePresenter.getArticles(categoryType, pageNumber)
                    isRefreshing = false
                }
            }
            recyclerViewArticles.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    if ((recyclerView.layoutManager as LinearLayoutManager).findLastVisibleItemPosition() == articlesAdapter.itemCount - 1) {
                        articlePresenter.getArticles(categoryType, ++pageNumber)
                    }
                }
            })
        }
    }

    override fun initData() {
        categoryType = arguments?.getSerializable(ARGUMENT_CATEGORY_NAME) as CategoryType
        ArticlePresenter(
            ArticleRepository.getInstance(
                ArticleRemoteDataSource.getInstance()
            )
        ).run {
            articlePresenter = this
            setView(this@ArticlesFragment)
            getArticles(categoryType, pageNumber)
        }
    }

    override fun onGetArticlesSuccess(articles: MutableList<Article>) {
        if (!articles.isNullOrEmpty()) {
            if (pageNumber == 1) {
                articlesAdapter.updateData(articles)
            } else {
                articlesAdapter.addData(articles)
            }
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
