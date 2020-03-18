package com.example.samplesms

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.samplesms.com.example.samplesms.entity.Message
import com.example.samplesms.com.example.samplesms.entity.MyData
import com.fasterxml.jackson.databind.ObjectMapper
import io.realm.Realm
import io.realm.RealmResults
import okhttp3.MediaType
import java.util.*

class HomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home, container, false)
    }
}

class ChatFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.chat, container, false)
    }

    val END_POINT: String = "https://fcm.googleapis.com/fcm/send"
    val SERVER_KEY: String =
        "AAAAlGgJKvg:APA91bGqsRC67pfpk9HIQz2MlrlDgc9maqCK92fJzSeZWRLCwggNZW9Z9OBoccumY_q0oNoct4nhdPvJB3cc8c4UP8VOoFAsJn02B3RUjR-UzZGWvk03TxbdeiK9mR_XAIQZmTK8rnFQ"

    override fun onStart() {
        super.onStart()
        val button: Button? = activity?.findViewById(R.id.sendButton)
        button?.setOnClickListener {
            val editText: EditText? = activity?.findViewById(R.id.textbox)
            var text: String? = editText?.text?.toString()

            // 入力内容が空の時は何もしない
            if (text == null || text == "") {
                return@setOnClickListener
            }

            Log.d("kakunin", text)



            // DB初期化
            Realm.init(context)
            val mRealm = Realm.getDefaultInstance()

            // 送信者の情報を取得
            val mydata: MyData? = mRealm.where(MyData::class.java).findFirst()


            var message: Message? = null
            mRealm.executeTransaction {
                // メッセージを登録
                message = mRealm.createObject(Message::class.java, UUID.randomUUID().toString())
                message?.fromUserName = mydata?.name
                message?.message = text
                message?.createAt = Date()
                message?.fromUserId = mydata?.id
            }

            val data: String =
                "{ \"to\" : \"/topics/sample_sms\",  \"data\" : { \"message\": \"hello\" } }"
            val mediaType = MediaType.parse("application/json")

            // メッセージ内容をjsonにする
            val mapper: ObjectMapper = ObjectMapper()
            val messageJson: String = mapper.writeValueAsString(message)

            Log.d("json", messageJson)


        }
    }
}