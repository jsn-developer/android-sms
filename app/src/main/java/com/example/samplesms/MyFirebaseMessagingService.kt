package com.example.samplesms

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage){
        super.onMessageReceived(remoteMessage)

        // ここに通知を受け取った時の処理を記述
        Log.d("Title:", remoteMessage?.notification?.title)
        Log.d("Message:", remoteMessage?.notification?.body)

        // 明示的にIntentの生成
        var intent: Intent = Intent(applicationContext, DialogActivity::class.java)

        // スタックにタスクが存在しても新しいタスクとして起動する設定
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

        // 通知内のタイトルとメッセージを格納する変数
        var title: String
        var message: String

        // 通知タイトル内容の決定
        if (remoteMessage!!.notification!!.title != null) { title = remoteMessage!!.notification!!.title!! }
        else { title = "代替プッシュ通知" }

        // 通知メッセージ内容の決定
        if (remoteMessage!!.notification!!.body != null) {message = remoteMessage!!.notification!!.body!! }
        else { message = "代替テスト通知" }

        //intentに情報の付加
        intent.putExtra("title", title)
        intent.putExtra("message", message)

        // Activityの起動
        startActivity(intent)
    }
}