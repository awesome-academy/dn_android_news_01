package com.edu.news.data.model

data class Category(
    val name: String,
    var position: Int,
    var enabled: Boolean
    )

object CategoryEntry {
    const val NAME = "name"
    const val POSITION = "position"
    const val ENABLE = "enable"
}
