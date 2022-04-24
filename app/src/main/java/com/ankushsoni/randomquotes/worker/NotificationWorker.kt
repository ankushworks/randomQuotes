package com.ankushsoni.randomquotes.worker

import android.content.Context
import android.provider.SyncStateContract
import android.util.Log
import androidx.work.ForegroundInfo
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.ankushsoni.randomquotes.QuotesApplication
import com.ankushsoni.randomquotes.Utils.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*


class NotificationWorker(val context : Context , params: WorkerParameters) : Worker(context, params){
    override fun doWork(): Result {
        val c: Date = Calendar.getInstance().getTime()
        Log.d("NotificationisWorking" , c.toString())
        val repository = (context as QuotesApplication).quotesRepository
        CoroutineScope(Dispatchers.IO).launch {
            repository.ShowNotfication()

        }
        return Result.success()
    }
}