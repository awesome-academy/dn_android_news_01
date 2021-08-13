package com.edu.news.data.source.local

import java.lang.Exception

interface OnFetchDataListener<T> {
    fun onSuccess(data: T)
    fun onError(exception: Exception)
}
