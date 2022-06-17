package com.codeliner.moviestutu.data.retrofit

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.codeliner.moviestutu.data.retrofit.api.RetrofitInstance
import com.codeliner.moviestutu.model.GitHubSearch
import com.codeliner.moviestutu.model.Item
import com.codeliner.moviestutu.paging.MoviesPagingSource
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class RetrofitRepository {
    suspend fun getMovies(): GitHubSearch { //pageIndex: Int
        return RetrofitInstance.api.getMovieReview()
    }

    fun getPagingMoviesFlow(): Flow<PagingData<Item>> {
        return Pager(PagingConfig(pageSize = 20)) {
            MoviesPagingSource(RetrofitInstance.api)
        }.flow
    }
}