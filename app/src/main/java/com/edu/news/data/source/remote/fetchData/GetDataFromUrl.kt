package com.edu.news.data.source.remote.fetchData

import android.os.Handler
import android.os.Looper
import com.edu.news.data.model.Article
import com.edu.news.data.source.OnFetchDataListener
import org.json.JSONObject
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class GetDataFromUrl {

    private val executor: Executor = Executors.newSingleThreadExecutor()
    private val handler = Handler(Looper.getMainLooper())
    private var data: MutableList<Article>? = null

    fun callAPI(listener: OnFetchDataListener<MutableList<Article>>, url: String, key: String) {
        executor.execute {
            val result = NetworkHelper().getJsonFromUrl(url + key)
            data = ParseJsonToData().parseJsonArray(JSONObject(result))
            handler.post {
                try {
                    data?.let { listener.onSuccess(it) }
                } catch (e: Exception) {
                    listener.onError(e)
                }
            }
        }
    }
}
