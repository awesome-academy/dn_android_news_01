package com.edu.news.data.model

data class Article(
    var id: String = "",
    var name: String = "",
    var author: String = "",
    var title: String = "",
    var description: String = "",
    var urlToImage: String = "",
    var publishedAt: String = "",
    var content: String = ""
)

object ArticleEntry {
    const val OBJECT = "articles"
    const val SOURCE = "source"
    const val ID = "id"
    const val NAME = "name"
    const val AUTHOR = "author"
    const val TITLE = "title"
    const val DESCRIPTION = "description"
    const val URL_IMAGE = "urlToImage"
    const val PUBLISHED_AT = "publishedAt"
    const val CONTENT = "content"
}
