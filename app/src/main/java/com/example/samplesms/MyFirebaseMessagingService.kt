package com.example.samplesms


import android.content.Intent
import android.util.Log
import com.example.samplesms.com.example.samplesms.activity.DialogActivity
import com.example.samplesms.com.example.samplesms.entity.Message
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import org.json.JSONException
import org.json.JSONObject
import com.fasterxml.jackson.databind.ObjectMapper
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import com.example.samplesms.com.example.samplesms.entity.MyData
import io.realm.Realm
import io.realm.RealmResults


class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        val mapper = jacksonObjectMapper()
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true)

        var message: Message = Message()

        // JSONをパースする
        try {
//            var json: JSONObject = JSONObject(remoteMessage?.data)
//            Log.d("rykomatsu",json.toString())
//            user = json.getString("user")
//            Log.d("rykomatsu",user.toString())
//            message = json.getString("message")
//            Log.d("rykomatsu",message.toString())

            // rxJavaをappレベルのbuild.gradleに記述しないと競合して動かない
            val json: String = mapper.writeValueAsString(remoteMessage?.data)
            message = mapper.readValue(json)
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        Realm.init(this)
        val mRealm = Realm.getDefaultInstance()
        mRealm.executeTransaction {
            mRealm.insert(
                Message(
                    "",
                    message?.fromUserName,
                    message?.message,
                    message?.createAt,
                    message?.isYourself
                )
            )
        }
        val messages: RealmResults<Message> = mRealm.where(Message::class.java).findAll()
        Log.d("message確認", message?.toString())

        // 明示的にIntentの生成
        var intent: Intent = Intent(applicationContext, DialogActivity::class.java)

        // スタックにタスクが存在しても新しいタスクとして起動する設定
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

        //intentに情報の付加
        intent.putExtra("title", message?.fromUserName)
        intent.putExtra("message", message?.message)

        // Activityの起動
        startActivity(intent)
    }
}