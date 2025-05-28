package com.example.noticias.utils

import android.content.Context
import android.content.SharedPreferences

class SessionManager (context: Context) {
    private val sharedPref = context.getSharedPreferences("noticias_session", Context.MODE_PRIVATE)

    fun setFavoriteNoticias(id: String) {
        val editor = sharedPref.edit()
        editor.putString("FAVORITE_NOTICIAS",id)
        editor.apply()

    }

    fun getFavoriteNoticias(): String {
        return sharedPref.getString("FAVORITE_NOTICIAS","")!!

    }

}