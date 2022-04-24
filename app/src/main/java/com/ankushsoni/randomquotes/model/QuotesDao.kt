package com.ankushsoni.randomquotes.model

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface QuotesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuotes(quotesTable: QuotesTable)

    @Delete
    suspend fun deleteQuotes(quotesTable: QuotesTable)


    @Query("Select * from Quotes")
    fun getQuotes(): LiveData<List<QuotesTable>>


    @Query("SELECT * FROM ListQuotes")
    fun getAllQuotes(): LiveData<List<ListQuotes>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertQuotesList(quote: ListQuotes)



}