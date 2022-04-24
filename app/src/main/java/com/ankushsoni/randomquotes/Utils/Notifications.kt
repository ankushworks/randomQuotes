package com.ankushsoni.randomquotes.Utils

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.app.TaskStackBuilder
import com.ankushsoni.randomquotes.MainActivity
import com.ankushsoni.randomquotes.R
import java.util.*


class Notifications(val context: Context) {
    private  var notificationCompat  = NotificationCompat.Builder(context, CHANNEL_ID)
    private lateinit var notificationManagerCompat : NotificationManagerCompat
    private lateinit var intent: Intent

    fun createNotification(content : String , author : String){

        intent = Intent(context,MainActivity::class.java)
        intent.putExtra("is_opened" , true)
        intent.putExtra("quote_content" , content)
        intent.putExtra("quote_author" , author)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

        val pendingIntent = TaskStackBuilder.create(context).run {
            addNextIntent(intent)
            getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT)
        }



        notificationCompat.apply {
            setContentText(content)
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                setSmallIcon(R.drawable.notifi_app_logo)
            }else{
                setSmallIcon(R.drawable.app_logo)
            }
            setContentIntent(pendingIntent)
            setAutoCancel(true)
            priority = NotificationCompat.PRIORITY_HIGH
        }
//        Random().nextInt(100)
        val m = (Date().time / 1000L % Int.MAX_VALUE).toInt()
        notificationManagerCompat = NotificationManagerCompat.from(context)
        notificationManagerCompat.notify(NOTIFICATION_ID, notificationCompat.build())

    }

}