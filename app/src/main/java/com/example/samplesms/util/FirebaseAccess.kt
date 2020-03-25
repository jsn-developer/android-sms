package com.example.samplesms.util

import com.example.samplesms.entity.Message
import com.example.samplesms.entity.MyData
import io.realm.Realm
import java.util.*

class FirebaseAccess {

    fun sendMessage(inputMessage: String) {
        val realm: Realm = Realm.getDefaultInstance()
        val mydata: MyData? = realm.where(MyData::class.java).findFirst()
        realm.executeTransaction {
            val message: Message =
                realm.createObject(Message::class.java, UUID.randomUUID().toString())
            message.fromUserId = mydata?.id
            message.fromUserName = mydata?.name
            message.message = inputMessage
        }
    }
}