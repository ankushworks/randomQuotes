package com.ankushsoni.randomquotes.network

import com.ankushsoni.randomquotes.model.ListQuotes
import com.ankushsoni.randomquotes.model.QuotesModel
import com.ankushsoni.randomquotes.model.TestingQuotesList
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface RetroServiceInterface {

    @GET("random")
    suspend fun getQuotes(): Response<QuotesModel>

    @GET(value = "quotes")
    suspend fun getListofQuotes(@Query("page") page : Int) : Response<TestingQuotesList>

    @GET("random")
    suspend fun getQuotesByCategory(@Query("tags") tags: String): Response<QuotesModel>

}