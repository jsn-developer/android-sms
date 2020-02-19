package com.example.samplesms

import android.content.Context
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import android.preference.PreferenceManager

/**
 * 設定ファイルの操作
 */
public class Configuration {

    private enum class Key {
        IS_FIRST
    }

    companion object {

        /**
         * 初回起動かどうかの判定
         */
        fun isFirst(context: Context): Boolean {
            val hoge: Boolean = PreferenceManager.getDefaultSharedPreferences(context)
                .getBoolean(Key.IS_FIRST.name, true)
            return hoge
        }

        /**
         * 初回起動フラグの更新
         */
        fun setFirstFlag(context: Context, isFirst: Boolean) {
            val edit: Editor = PreferenceManager.getDefaultSharedPreferences(context).edit()
            edit.putBoolean(Key.IS_FIRST.name, isFirst)
            edit.apply()
        }
    }
}