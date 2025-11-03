package com.example.projectsaveuser.helper

import android.content.Context
import android.content.SharedPreferences
import com.example.projectsaveuser.model.User
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SharedPreferenceManager(context: Context) {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
    private val editor: SharedPreferences.Editor = sharedPreferences.edit()
    private val gson = Gson()

    fun saveUserData(firstName: String, lastName: String, age: String) {
        val userList = getUserList().toMutableList()
        userList.add(User(firstName, lastName, age))
        val json = gson.toJson(userList)
        editor.putString("user_list", json)
        editor.apply()
    }

    fun getUserList(): List<User> {
        val json = sharedPreferences.getString("user_list", null)
        return if (json != null) {
            val type = object : TypeToken<List<User>>() {}.type
            gson.fromJson(json, type)
        } else {
            emptyList()
        }
    }

    fun clearUserData() {
        editor.clear()
        editor.apply()
    }
}
