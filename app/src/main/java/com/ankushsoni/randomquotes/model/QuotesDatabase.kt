package com.ankushsoni.randomquotes.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [QuotesTable::class , ListQuotes::class] , version = 1)
abstract class QuotesDatabase : RoomDatabase(){

    abstract fun quotesDao() : QuotesDao


    companion object{
        @Volatile
        private var INSTANCE  : QuotesDatabase? = null


        fun getDatabase(context: Context) : QuotesDatabase{
            if(INSTANCE == null) run {
                synchronized(this){
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        QuotesDatabase::class.java,
                        "QuotesDB"
                    )
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return  INSTANCE!!
        }
    }
}