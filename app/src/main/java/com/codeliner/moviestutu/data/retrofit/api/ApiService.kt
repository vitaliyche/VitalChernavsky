package com.codeliner.moviestutu.data.retrofit.api

import com.codeliner.moviestutu.MainActivity.Companion.REQUEST
import com.codeliner.moviestutu.model.GitHubSearch
import retrofit2.http.GET

interface ApiService {
    @GET("search/repositories?q=kotlin+retrofit")
    suspend fun getMovieReview(): GitHubSearch
}