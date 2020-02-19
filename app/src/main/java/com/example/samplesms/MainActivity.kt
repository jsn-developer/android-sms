package com.example.samplesms

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tab)

        pager.adapter = TabAdapter(supportFragmentManager,this)
        tab_layout.setupWithViewPager(pager)
    }
}
