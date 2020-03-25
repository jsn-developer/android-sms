package com.example.samplesms.com.example.samplesms.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.samplesms.Configuration
import com.example.samplesms.R
import com.example.samplesms.TabAdapter
import com.example.samplesms.com.example.samplesms.entity.Message
import com.example.samplesms.com.example.samplesms.entity.MyData
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId
import io.realm.Realm
import io.realm.RealmResults
import kotlinx.android.synthetic.main.activity_main.*

/**
 * ホーム画面のアクティビティ
 */
class MainActivity : AppCompatActivity() {

    val REQUEST_CODE = 1

    fun dbCheck() {
        // DB内の確認
        val messages: RealmResults<Message> =
            Realm.getDefaultInstance().where(Message::class.java).findAll()
        Log.d("message確認", "<---- ここから---->")
        for (m in messages) {
            Log.d("message確認", m?.createAt?.toString())
            Log.d("message確認", m?.fromUserId?.toString())
            Log.d("message確認", m?.fromUserName?.toString())
            Log.d("message確認", m?.message?.toString())
            Log.d("message確認", m?.id?.toString())
        }
        Log.d("message確認", "<---- ここまで ---->")
    }

    fun getMyData(): MyData?{
        return Realm.getDefaultInstance().where(MyData::class.java).findFirst()
    }

    fun getToken() {
        // トークン取得
        FirebaseInstanceId.getInstance().instanceId
            .addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    return@OnCompleteListener
                }

                // Get new Instance ID token
                val token = task.result?.token

                // Log and toast
                Log.d("token", token.toString())
            })
    }

    fun createFragment() {
        // フラグメント描画
        pager.adapter = TabAdapter(supportFragmentManager, this)
        tab_layout.setupWithViewPager(pager)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        getToken()
        dbCheck()

        // もし初回起動ならスタート画面に遷移する
        if (Configuration.isFirst(applicationContext)) {
            val intent = Intent(this, InitializerActivity::class.java)
            startActivityForResult(intent, this.REQUEST_CODE)
        }

        // 画面描画
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createFragment()

    }

    /**
     * スタート画面から戻ってきたときの処理
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Initializerから戻ってきた場合
        if (requestCode == this.REQUEST_CODE) {
            val mRealm = Realm.getDefaultInstance()
            val user = mRealm.where(MyData::class.java).findFirst()
            Log.d("username", user?.name)
        }
    }
}
