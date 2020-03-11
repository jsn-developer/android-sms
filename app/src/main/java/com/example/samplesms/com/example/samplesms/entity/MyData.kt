package com.example.samplesms.com.example.samplesms.entity

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.Required
import java.util.*

open class MyData(
    /**
     * 主キー
     * 重複しないランダムな文字列を割り当て
     */
    @PrimaryKey open var id : String = UUID.randomUUID().toString(),

    /**
     * ユーザー名
     */
    @Required open var name : String = ""
) : RealmObject(){}