package com.example.samplesms

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class TabAdapter(fm:FragmentManager, private val context: Context): FragmentPagerAdapter(fm){

    override fun getItem(position: Int): Fragment {
        when(position){
            0 -> { return HomeFragment() }
            else ->  { return ChatFragment() }
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when(position){
            0 -> { return "メンバー" }
            else ->  { return "会話" }
        }
    }

    override fun getCount(): Int {
        return 2
    }
}