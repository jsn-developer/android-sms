package com.example.samplesms.com.example.samplesms.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.samplesms.Configuration
import com.example.samplesms.R
import io.realm.Realm
import com.example.samplesms.com.example.samplesms.entity.MyData
import com.google.firebase.messaging.FirebaseMessaging


/**
 * 初回起動のアクティビティ
 */
class InitializerActivity : AppCompatActivity() {

    /**
     * 初回起動時の処理
     */
    override fun onCreate(savedInstanceState: Bundle?) {

        // スタート画面にはアクションバーはいらないので隠す
        val actionBar = supportActionBar
        actionBar!!.hide()

        // 画面描画
        super.onCreate(savedInstanceState)
        setContentView(R.layout.start)

        val intent = Intent()

        // ボタンイベントを生成
        val button: Button = findViewById(R.id.startButton)
        button.setOnClickListener {

            val name = findViewById<EditText>(R.id.editText).text.toString()

            // 名前の入力が無い場合は入力を促して終了
            // バリデーションで英, 漢字, 一部記号や数字のみ入力可能にしたい
            // ~[英漢]+[記数英漢]*$　など
            if (name == null || name == "") {
                Toast.makeText(applicationContext, "名前を入力してください", Toast.LENGTH_SHORT).show();
                return@setOnClickListener
            } else {

                // ユーザー情報をrealmに保存
                saveUserInfo(name)

                // 初回起動フラグをOFF
                Configuration.setFirstFlag(applicationContext, false)

                // 呼び出し元Activityに戻る
                setResult(Activity.RESULT_OK, intent)
                finish()
            }
        }
    }

    fun createToken(){
    }

    /**
     * ユーザー情報をDBに保存する
     */
    fun saveUserInfo(name: String) {
        Realm.init(this)
        val mRealm = Realm.getDefaultInstance()
        mRealm.executeTransaction {
            mRealm.insert(MyData("1", name))
        }

        // 通信のため、トピック（グループ）をfirebaseに登録する
        FirebaseMessaging.getInstance().subscribeToTopic("sample_sms")
            .addOnCompleteListener { task ->
                var msg = "true"
                if (!task.isSuccessful) {
                    msg = "false"
                }
                Log.d("rykomatsu", msg)
                Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
            }
    }
}