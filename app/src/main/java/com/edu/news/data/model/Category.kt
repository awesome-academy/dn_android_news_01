package com.edu.news.data.model

data class Category(
    var type: CategoryType,
    var position: Int,
    var enabled: Boolean
    )

object CategoryEntry {
    const val TYPE = "type"
    const val POSITION = "position"
    const val ENABLE = "enable"
}

enum class CategoryType(val nameType: String) {
    TOP_HEADLINE("Top-Headline"),
    SPORT("Sports"),
    TECHNOLOGY("Technology"),
    BUSINESS("Business"),
    ENTERTAINMENT("Entertainment"),
    HEALTH("Health"),
    SCIENCE("Science"),
    GENERAL("General"),
}
