package com.example.noticias

import android.content.Context
import androidx.room.util.TableInfo
import androidx.room.util.getColumnIndex

object SharedPreferencesUtil {
    private const val PREF_NAME = "news_app_prefs"
    private const val KEY_USER_NAME = "user_name"

    fun saveUserName(context: Context, userName: String) {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        prefs.edit().putString(KEY_USER_NAME, userName).apply()
    }

    fun getUserName(context: Context): String? {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return prefs.getString(KEY_USER_NAME, null)
    }
}
