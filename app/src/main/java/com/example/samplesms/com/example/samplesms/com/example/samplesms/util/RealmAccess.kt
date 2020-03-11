package com.example.samplesms.com.example.samplesms.com.example.samplesms.util

import android.util.Log
import com.example.samplesms.com.example.samplesms.entity.MyData
import io.realm.Realm

class RealmAccess {
    fun getUser(){
        val mRealm = Realm.getDefaultInstance()
        val user = mRealm.where(MyData::class.java).findFirst()
        Log.d("rykomatsu",user?.name)
    }

    fun setUser(userName: String){
        val mRealm = Realm.getDefaultInstance()
        mRealm.beginTransaction()
        val model = mRealm.createObject(MyData::class.java)
        model.name = userName
        mRealm.commitTransaction()
    }
}