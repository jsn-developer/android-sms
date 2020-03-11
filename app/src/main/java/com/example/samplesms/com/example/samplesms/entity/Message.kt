package com.example.samplesms.com.example.samplesms.entity

import com.fasterxml.jackson.annotation.JsonProperty
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.Required
import java.util.*

open class Message(
    /**
     * 主キー
     * 重複しないランダムな文字列を割り当て
     */
    @PrimaryKey open var id : String = UUID.randomUUID().toString(),

    /**
     * 送信ユーザー名
     */
    @Required @JsonProperty("user_name") open var fromUserName : String = "",

    /**
     * メッセージ内容
     */
    @Required @JsonProperty("message") open var message : String = "",

    /**
     * 受信時刻
     */
    @Required @JsonProperty("create_at") open var createAt : Date = Date(),

    /**
     * 送信者識別子
     * true: 自分が送ったメッセージ
     * false: 受信したメッセージ
     */
     @JsonProperty("is_yourself") open var isYourself : Boolean = false
) : RealmObject(){}