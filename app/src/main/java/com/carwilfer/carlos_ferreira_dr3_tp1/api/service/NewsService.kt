package com.carwilfer.carlos_ferreira_dr3_tp1.api.service

import com.carwilfer.carlos_ferreira_dr3_tp1.model.News
import com.carwilfer.carlos_ferreira_dr3_tp1.model.ResponseTypes
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    //listar
    @GET("svc/search/v2/articlesearch.json")
    suspend fun all(@Query("api-key") news: String = "iaI80hGAwf9sE11YuPbkPcO2oG3jbUTw"): List<ResponseTypes>


}