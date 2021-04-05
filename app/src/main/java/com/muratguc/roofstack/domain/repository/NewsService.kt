package com.muratguc.roofstack.domain.repository

import com.muratguc.roofstack.data.model.NewsListResponseModel
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Murat Güç on 2/1/2021.
 */
interface NewsService {

    @GET("/v2/everything")
    suspend fun getNews(
        @Query("q") filterQuery: String? = "football",
        @Query("apiKey") apiKey: String? = "ae68088e70d04639b4950bdc9d546924",
        @Query("sortBy") sortBy: String? = "publishedAt",
        @Query("from") from: String? = "publishedAt",
        @Query("to") to: String? = "publishedAt",
        @Query("page") page: Int,
        @Query("per_page") pageSize: Int,
    ): NewsListResponseModel
}