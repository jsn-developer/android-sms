package com.example.samplesms

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.widget.Button

/**
 * 初回起動のアクティビティ
 */
class InitializerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        // スタート画面にはアクションバーはいらないので隠す
        val actionBar = supportActionBar
        actionBar!!.hide()

        // 画面描画
        super.onCreate(savedInstanceState)
        setContentView(R.layout.start)

        // ボタンイベントを生成
        val button: Button = findViewById(R.id.startButton)
        button.setOnClickListener {

            // TODO ユーザー情報をrealmに保存

            // 初回起動フラグをOFF
            Configuration.setFirstFlag(applicationContext, false)

            // ホーム画面に遷移
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}