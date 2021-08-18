package com.edu.news.utils.base

interface BasePresenter<T>{
    fun onStart()
    fun setView(view: T?)
}
