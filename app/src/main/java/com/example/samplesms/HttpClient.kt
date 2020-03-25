package com.example.samplesms


import java.io.IOException

import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

class HttpClient : Callback {
    fun getWithUrlString(url: String) {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(url)
            .build()

        client.newCall(request).enqueue(this)
    }

    override fun onFailure(call: Call, e: IOException) {
        println("onFailure")
    }

    @Throws(IOException::class)
    override fun onResponse(call: Call, response: Response) {
        println("Status code: " + response.code())
        println("Body: " + response.body()!!.string().substring(0, 19) + "...")
    }

}