package com.codeliner.vitalchernavsky.data.retrofit.api

import com.codeliner.vitalchernavsky.model.GitHubSearch
import retrofit2.http.GET

interface ApiService {
    @GET("search/repositories?q=user:vitaliyche")
    suspend fun getMovieReview(): GitHubSearch
}