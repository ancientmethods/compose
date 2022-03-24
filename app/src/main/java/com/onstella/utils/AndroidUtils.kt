package com.onstella.utils

import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.ui.graphics.toArgb
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.onstella.ui.theme.SecondaryNavy

fun Context.succesToast(msg:String){
    Toast.makeText(this,msg,Toast.LENGTH_LONG).show()
}

/**
 * passing a lambda with receiver to a higher order function
 */
fun notification(context: Context,channelID:String, contentText:String){

    //here we remove the "it" because its calling a lambda with a reciever
    context.notifyMsg(channelID){
        color = SecondaryNavy.toArgb()
        setContentText(contentText)
        setSmallIcon(0)
    }

}



/**
 * this is with lambda with reciever // see the difference with the reciever in the function parameter below
 */
inline fun Context.notifyMsg(channelID:String, notificationBody:NotificationCompat.Builder.()-> Unit) {

    var builder = NotificationCompat.Builder(this, channelID)
    builder.notificationBody()

    val notification = builder.build()
    var notificationManager = ContextCompat.getSystemService(this, NotificationManager::class.java)
    notificationManager?.notify(0, notification)
}

/**
 * this is without lambda with reciever
 */
/*
fun Context.notify( body:(NotificationCompat.Builder)-> Unit){

    body.invoke(b)
}*/


/**
 * using kotlin scope functions
 * notice how the type of intent changes depending on the last statement in the lambda
 */
fun scopeKotlin(){

    var intent = Intent().apply {
        putExtra("","value")
        putExtra("","better than accessing the object")
        5
    }
    var intent2 = with(Intent()) {
        putExtra("","value")
        putExtra("","better than accessing the object")
        4
    }

    var intent3 = Intent().run {
        putExtra("","value")
        putExtra("","better than accessing the object")
        3
    }

}
