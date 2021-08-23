package com.edu.news.screen.home.articles

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.edu.news.data.model.Article
import com.edu.news.data.source.remote.fetchData.LoadImageFromUrl
import com.sun.news.R
import kotlinx.android.synthetic.main.item_article.view.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ArticlesAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val articles = mutableListOf<Article?>()

    fun updateData(data: MutableList<Article>?) {
        data?.let {
            articles.clear()
            articles.addAll(it)
            articles.add(null)
            notifyDataSetChanged()
        }
    }

    fun addData(data: MutableList<Article>?) {
        val oldSize = articles.size
        data?.let {
            articles.removeAt(oldSize - 1)
            notifyItemRemoved(oldSize - 1)
            articles.addAll(it)
            articles.add(null)
            notifyItemRangeInserted(oldSize - 1, it.size + 1)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_DATA) {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_article, parent, false)
            ArticleViewHolder(view)
        } else {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_progressbar, parent, false)
            ProgressViewHolder(view)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when(articles[position]) {
            null -> VIEW_TYPE_PROGRESS
            else -> VIEW_TYPE_DATA
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        articles[position]?.let { ArticleViewHolder(holder.itemView).bind(it) }
    }

    override fun getItemCount() = articles.size

    class ArticleViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(article: Article) = with(itemView) {
            article.let {
                textViewTitle.text = it.title
                textViewDateTime.text =
                    LocalDateTime.parse(it.publishedAt, DateTimeFormatter.ISO_DATE_TIME)
                        .format(DateTimeFormatter.ofPattern(DATETIME_FORMAT))
                textViewPublisher.text = it.name
                LoadImageFromUrl(imageArticle).loadImage(it.urlToImage)
            }
        }
    }

    class ProgressViewHolder(view: View) : RecyclerView.ViewHolder(view)

    companion object {
        private const val VIEW_TYPE_DATA = 0
        private const val VIEW_TYPE_PROGRESS = 1
        private const val DATETIME_FORMAT = "dd.MM.yyyy HH:mm"
    }
}
