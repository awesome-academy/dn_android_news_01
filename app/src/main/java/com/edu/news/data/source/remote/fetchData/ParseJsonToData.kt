package com.edu.news.data.source.remote.fetchData

import com.edu.news.data.model.Article
import com.edu.news.data.model.ArticleEntry
import org.json.JSONObject

class ParseJsonToData {

    private fun parseJson(jsonObject: JSONObject) = jsonObject.run {
        Article(
            getJSONObject(ArticleEntry.SOURCE).getString(ArticleEntry.ID),
            getJSONObject(ArticleEntry.SOURCE).getString(ArticleEntry.NAME),
            getString(ArticleEntry.AUTHOR),
            getString(ArticleEntry.TITLE),
            getString(ArticleEntry.DESCRIPTION),
            getString(ArticleEntry.URL_IMAGE),
            getString(ArticleEntry.PUBLISHED_AT),
            getString(ArticleEntry.CONTENT)
        )
    }

    fun parseJsonArray(jsonObject: JSONObject): MutableList<Article> {
        val data = mutableListOf<Article>()
        jsonObject.getJSONArray(ArticleEntry.OBJECT).let { array ->
            for (i in 0 until array.length()) {
                data.add(parseJson(array.getJSONObject(i)))
            }
        }
        return data
    }
}
