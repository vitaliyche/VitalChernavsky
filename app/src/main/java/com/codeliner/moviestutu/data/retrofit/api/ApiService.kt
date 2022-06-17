package com.codeliner.moviestutu.data.retrofit.api

import com.codeliner.moviestutu.model.GitHubSearch
import retrofit2.http.GET

interface ApiService {
    @GET("search/repositories?q=retrofit+kotlin")
    suspend fun getMovieReview(): GitHubSearch
}