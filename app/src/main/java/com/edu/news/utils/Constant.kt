package com.edu.news.utils

import com.sun.news.BuildConfig

enum class MenuItem {
    HOME_ITEM,
    FAVORITE_ITEM,
    FOLLOWS_ITEM
}

object Constant {
    const val BASE_URL = "https://newsapi.org/v2/top-headlines"
    const val BASE_COUNTRY = "?country=us"
    const val PAGE_SIZE = "&pageSize=15"
    const val BASE_PAGE = "&page=1"
    const val BASE_CATEGORY_PARAM = "&category="
    const val BASE_API_KEY = "&apikey=${BuildConfig.API_KEY}"
}
