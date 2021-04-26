package com.altkamul.testapp.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.altkamul.testapp.model.Articles
import com.altkamul.testapp.network.NewsApi
import retrofit2.Response
import javax.inject.Inject


/*************************
 * Created by AZAT SAYAN *
 *                       *
 * Contact: @theazat     *
 *                       *
 * 23/08/2020 - 11:51 AM  *
 ************************/
class RemoteRepository @Inject constructor(
    private val newsApi: NewsApi
) : BaseRepository() {

    suspend fun getAllNews(): MutableList<Articles>? {
        return safeApiCall(
                call = { newsApi.getNews() },
                error = "Error fetching news"
        )?.articles?.toMutableList()
    }

    suspend fun searchNews(searchQuery: String): MutableList<Articles>? {
        return safeApiCall(
                call = { newsApi.getNews(searchQuery) },
                error = "Error fetching news"
        )?.articles?.toMutableList()
    }
    }


