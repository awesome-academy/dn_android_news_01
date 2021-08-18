package com.edu.news.data.source.local

import java.lang.Exception

interface OnPostDataListener<T> {
    fun onError(exception: Exception)
}
