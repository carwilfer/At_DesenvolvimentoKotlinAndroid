package com.carwilfer.carlos_ferreira_dr3_tp1.api

import com.carwilfer.carlos_ferreira_dr3_tp1.api.service.NewsService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    //instancia da retrofit
    private var instace: Retrofit? = null
    private fun getRetrofit(): Retrofit{
        if (instace == null)
            instace = Retrofit.Builder()
                .baseUrl("https://api.nytimes.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        return instace as Retrofit
    }

    //funcoes que chamem retrofit.create(Service::class.java)
    fun getNewsService(): NewsService =
        getRetrofit().create(NewsService::class.java)
}