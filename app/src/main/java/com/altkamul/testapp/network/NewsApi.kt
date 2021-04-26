package com.altkamul.testapp.network

import com.altkamul.testapp.model.NewsResponce
import com.altkamul.testapp.util.Constant.Constant.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/*************************
 * Created by AZAT SAYAN *
 *                       *
 * Contact: @theazat     *
 *                       *
 * 22/08/2020 - 6:30 PM  *
 ************************/
interface NewsApi {

    @GET("v2/top-headlines")
    suspend fun getNews(
        @Query("country")
        countryCode: String = "us",
        @Query("apiKey")
        apiKey: String = API_KEY
    ): Response<NewsResponce>


}