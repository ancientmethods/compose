package com.onstella.utils

/**
 * if you provide a default implementation in the interface then classes don't have to implement the method
 * also an example of good abstraction here with an interface
 *
 * If we decide on a different way to authentication, we can simply change the dagger Authentication object
 */

class FirebaseAuthentication: Authenticator{
    override fun logErrors() {
    }

    override suspend fun loginInWithUsernameAndPassword(username: String,password: String) {
        //firebase get instance etc...
    }

}

class CustomApiAuthenticator: Authenticator{
    override fun logErrors() {
        TODO("Not yet implemented")
    }

    override suspend fun loginInWithUsernameAndPassword(username: String, password: String) {
        TODO("Not yet implemented")
    }

}

interface Authenticator {

    fun logOptionalErrors(){}
    fun logErrors()
    suspend fun loginInWithUsernameAndPassword(username:String, password:String)

}