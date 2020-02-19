package com.example.samplesms

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

/**
 * ホーム画面のアクティビティ
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        // もし初回起動ならスタート画面に遷移
        if (Configuration.isFirst(applicationContext)) {
            val intent = Intent(this, InitializerActivity::class.java)
            startActivity(intent)
        }

        // 画面描画「
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // フラグメント描画
        pager.adapter = TabAdapter(supportFragmentManager, this)
        tab_layout.setupWithViewPager(pager)
    }
}
