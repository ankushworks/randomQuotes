package com.ankushsoni.randomquotes

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import androidx.work.*
import com.ankushsoni.randomquotes.Utils.CHANNEL_ID
import com.ankushsoni.randomquotes.Utils.CHANNEL_NAME
import com.ankushsoni.randomquotes.model.QuotesDatabase
import com.ankushsoni.randomquotes.network.RetroInstance
import com.ankushsoni.randomquotes.network.RetroServiceInterface
import com.ankushsoni.randomquotes.repository.QuotesRepository
import com.ankushsoni.randomquotes.worker.NotificationWorker
import java.util.concurrent.TimeUnit


class QuotesApplication : Application() {

    lateinit var quotesRepository: QuotesRepository

    override fun onCreate() {
        super.onCreate()
        initialize()
        createNotificationChannel()
        setupWorker()
    }

    private fun setupWorker() {
        val constraintsBuilder: Constraints.Builder = Constraints.Builder()
        constraintsBuilder.setRequiresBatteryNotLow(false)
        constraintsBuilder.setRequiredNetworkType(NetworkType.CONNECTED)
        constraintsBuilder.setRequiresCharging(false)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            constraintsBuilder.setRequiresDeviceIdle(false);
        }

        val constraint = constraintsBuilder.build()

        val workerRequest = PeriodicWorkRequest.Builder(NotificationWorker::class.java , 20, TimeUnit.MINUTES)
            .setConstraints(constraint)
            .build()
//        WorkManager.getInstance(this).enqueue(workerRequest)
          WorkManager.getInstance(this).enqueueUniquePeriodicWork("NotificationWorker" , ExistingPeriodicWorkPolicy.KEEP , workerRequest )
    }

    private fun initialize(){
        val dao = QuotesDatabase.getDatabase(applicationContext).quotesDao()
        val quoteService = RetroInstance.getRetroInstance().create(RetroServiceInterface::class.java)
        quotesRepository = QuotesRepository(dao,quoteService , applicationContext)

    }


    fun createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_DEFAULT
                )
            val notificationManager =
                applicationContext.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}