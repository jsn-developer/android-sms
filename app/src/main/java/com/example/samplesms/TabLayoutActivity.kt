package com.example.samplesms

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.tab.*

class TabLayoutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tab)

        pager.adapter = TabAdapter(supportFragmentManager,this)
        tab_layout.setupWithViewPager(pager)
    }
}