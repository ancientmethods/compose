package com.onstella.utils

import android.content.SharedPreferences
import javax.inject.Inject

class PreferencesManager @Inject constructor(var sharedPreferences: SharedPreferences) {

    var KEY_TOKEN = "token"

    fun saveToken(token:String){
        sharedPreferences.edit {
            putString(KEY_TOKEN,token)
        }
    }
}
/**
 * use inline to avoid new creation of Editor() function object
 */
inline fun SharedPreferences.edit(
    commit:Boolean = false,
    action:SharedPreferences.Editor.()->Unit
){
    val editor = edit()
    action(editor)
    if(commit){
        editor.commit()
    }
    else{
        editor.apply()
    }
}