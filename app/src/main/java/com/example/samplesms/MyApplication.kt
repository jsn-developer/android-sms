package com.example.samplesms

import android.app.Application
import io.realm.Realm

class MyApplication : Application() {
    companion object {

        private var realm: Realm? = null
        fun getRealm(): Realm? {
            return realm
        }
    }

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        realm = Realm.getDefaultInstance()
    }
}