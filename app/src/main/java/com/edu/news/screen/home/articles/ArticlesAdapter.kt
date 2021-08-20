package com.edu.news.screen.home.articles

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.edu.news.data.model.Article
import com.edu.news.data.source.remote.fetchData.LoadImageFromUrl
import com.sun.news.R
import kotlinx.android.synthetic.main.item_article.view.*

class ArticlesAdapter : RecyclerView.Adapter<ArticlesAdapter.ArticleViewHolder>() {

    private val articles = mutableListOf<Article>()

    fun updateData(data: MutableList<Article>?) {
        data?.let {
            articles.clear()
            articles.addAll(it)
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_article, parent, false)
        return ArticleViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.bind(articles[position])
    }

    override fun getItemCount() = articles.size

    class ArticleViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(article: Article) {
            itemView.apply {
                textViewTitle.text = article.title
                textViewDateTime.text = article.publishedAt
                LoadImageFromUrl(imageArticle).loadImage(article.urlToImage)
            }
        }
    }
}
