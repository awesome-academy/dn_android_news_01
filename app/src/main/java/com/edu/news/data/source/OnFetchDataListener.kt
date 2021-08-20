package com.edu.news.data.source

import java.lang.Exception

interface OnFetchDataListener<T> {
    fun onSuccess(data: T)
    fun onError(exception: Exception)
}
