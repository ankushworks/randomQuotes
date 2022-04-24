package com.ankushsoni.randomquotes.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ankushsoni.randomquotes.Utils.NetworkUtils
import com.ankushsoni.randomquotes.Utils.Notifications
import com.ankushsoni.randomquotes.model.ListQuotes
import com.ankushsoni.randomquotes.model.QuotesDao
import com.ankushsoni.randomquotes.model.QuotesModel
import com.ankushsoni.randomquotes.model.QuotesTable
import com.ankushsoni.randomquotes.network.RetroServiceInterface
import java.util.ArrayList

class QuotesRepository(private val quotesDao: QuotesDao, private val quoteService: RetroServiceInterface, private val  context: Context) {


    private val quoteLiveData  = MutableLiveData<QuotesModel>()
    val quoteData : LiveData<QuotesModel>
    get() = quoteLiveData

    //

    private val listQuotesLiveData = MutableLiveData<List<ListQuotes>>()
    val listQuoteData : LiveData<List<ListQuotes>>
        get() = listQuotesLiveData


    suspend fun getQuoteFromRetrofit(){
        if(NetworkUtils.isNetworkAvailable(context)){
            val result  = quoteService.getQuotes()
            if(result?.body() != null){
                quoteLiveData.postValue(result.body())
            }
        }else{
            val arrayList = ArrayList<String>()
            arrayList.add("Network Error")
            quoteLiveData.postValue(QuotesModel("1",arrayList ,"No Network Connection Available  Check Your Network Connection!!","Quotify","Testing" , 12,"2020123" ,"21332"))
        }
    }


    suspend fun setQuotesListInToDb(page : Int){
        if(NetworkUtils.isNetworkAvailable(context)){
            val result  = quoteService.getListofQuotes(page)
            Log.d("ResultFromApi" , result.body()!!.results.toString())
            if(result.body() != null){
                for (i in result.body()!!.results) {
                    Log.d("ListData", i.toString())
                    quotesDao.insertQuotesList(ListQuotes(0,i.content!!,i.author!!))
                }
            }
        }
    }



    suspend fun ShowNotfication(){
        val result  = quoteService.getQuotes()
        if(result?.body() != null){
            result.body()!!.content?.let { Notifications(context).createNotification(it,
                result.body()!!.author.toString())
            }
        }
    }


    fun getQuotes() : LiveData<List<QuotesTable>>{
        return quotesDao.getQuotes()
    }

    suspend fun insertQuote(quote : QuotesTable){
        quotesDao.insertQuotes(quote)
    }

    suspend fun deleteQuote(quote : QuotesTable){
        quotesDao.deleteQuotes(quote)
    }

    fun getAllQuotesList() : LiveData<List<ListQuotes>>{
        return  quotesDao.getAllQuotes()
    }

}