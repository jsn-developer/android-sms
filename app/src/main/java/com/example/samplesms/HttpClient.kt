package com.example.samplesms


import com.example.samplesms.entity.Message
import okhttp3.*
import java.io.IOException

class HttpClient : Callback {

    val URL = "https://fcm.googleapis.com/fcm/send"
    val CONTENT_TYPE_KEY = "Content-Type"
    val CONTENT_TYPE_VALUE = "application/json"
    val AUTHORIZATION_KEY = "Authorization"
    val AUTHORIZATION_VALUE =
        "key=AAAAlGgJKvg:APA91bGqsRC67pfpk9HIQz2MlrlDgc9maqCK92fJzSeZWRLCwggNZW9Z9OBoccumY_q0oNoct4nhdPvJB3cc8c4UP8VOoFAsJn02B3RUjR-UzZGWvk03TxbdeiK9mR_XAIQZmTK8rnFQ"
    val JSON =
//        "{ \"to\" : \"/topics/sample_sms\",  \"data\" : { \"user_name\": \"%s\",\"user_id\": \"%s\", \"create_at\": \"%s\", \"message\": \"%s\" } }"
        "{ \"to\" : \"/topics/sample_sms\",  \"data\" : { \"user_name\": \"%s\",\"user_id\": \"%s\",  \"message\": \"%s\" } }"


    fun getWithUrlString(message: Message) {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(URL)
            .addHeader(CONTENT_TYPE_KEY, CONTENT_TYPE_VALUE)
            .addHeader(AUTHORIZATION_KEY, AUTHORIZATION_VALUE)
            .post(
                RequestBody.create(
                    MediaType.parse("application/json;charset=utf-8"),
                    JSON.format(
                        message.fromUserName,
                        message.fromUserId,
                        message.message
                    )
                )
            )
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