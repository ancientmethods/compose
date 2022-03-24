package com.onstella.repositories

import com.onstella.utils.Authenticator
import javax.inject.Inject

/**
 * in case our authentication changes from a firebase to a custom one, we only need to change the appmodule provide method for Authenticator
 * any class that implements it will work here
 */
class MainRepository @Inject constructor(
    private val authenticator: Authenticator
) {

    suspend fun login(username:String, password:String){
        authenticator.loginInWithUsernameAndPassword(username,password)
    }
}