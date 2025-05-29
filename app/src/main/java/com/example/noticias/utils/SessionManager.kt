package com.example.noticias.utils

import android.content.Context
import android.content.SharedPreferences
import com.example.noticias.data.Usuario

class SessionManager (context: Context) {

    companion object {
        private const val PREF_NAME = "noticias_session"
        private const val KEY_USER_NAME = "user_name"
        private const val KEY_EMAIL = "email"
    }

    private val sharedPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    fun setUser(user: Usuario) {
        val editor = sharedPref.edit()
        editor.putString(KEY_USER_NAME, user.nombre)
        editor.putString(KEY_EMAIL, user.email)
        editor.apply()

    }

    fun removeUser() {
        val editor = sharedPref.edit()
        editor.remove(KEY_USER_NAME)
        editor.remove(KEY_EMAIL)
        editor.apply()

    }

    fun getUser(): Usuario? {
        val email = sharedPref.getString(KEY_EMAIL, null)
        val username = sharedPref.getString(KEY_USER_NAME, null)
        if (email != null && username != null) {
            return Usuario(username, email)
        } else {
            return null
        }
    }

}