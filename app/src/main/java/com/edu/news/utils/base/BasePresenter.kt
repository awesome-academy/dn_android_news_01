package com.edu.news.utils.base

interface BasePresenter<T>{
    fun setView(view: T?)
}
