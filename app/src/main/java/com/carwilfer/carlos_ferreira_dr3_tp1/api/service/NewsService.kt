package com.carwilfer.carlos_ferreira_dr3_tp1.api.service

import com.carwilfer.carlos_ferreira_dr3_tp1.model.News
import com.carwilfer.carlos_ferreira_dr3_tp1.model.ResponseTypes
import retrofit2.http.*

interface NewsService {

    //listar
    @GET("svc/search/v2/articlesearch.json")
    suspend fun all(@Query("api-key") news: String = "iaI80hGAwf9sE11YuPbkPcO2oG3jbUTw"): List<ResponseTypes>

    @GET("api-key/{id}")
    suspend fun read(@Path("id") id: Long)

    /*@POST("api-key")
    suspend fun insert(@Body responseTypes: ResponseTypes)

    @PUT("api-key/{id}")
    suspend fun update(
        @Path("id") id: Long,
        @Body responseTypes: ResponseTypes
    )

    @DELETE("api-key/{id}")
    suspend fun delete(@Path("id") id: Long)*/


}