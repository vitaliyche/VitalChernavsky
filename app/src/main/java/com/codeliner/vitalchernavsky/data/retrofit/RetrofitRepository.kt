package com.codeliner.vitalchernavsky.data.retrofit

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.codeliner.vitalchernavsky.data.retrofit.api.RetrofitInstance
import com.codeliner.vitalchernavsky.model.GitHubSearch
import com.codeliner.vitalchernavsky.model.Item
import com.codeliner.vitalchernavsky.paging.GithubPagingSource
import kotlinx.coroutines.flow.Flow

class RetrofitRepository {

    suspend fun getMovies(): GitHubSearch {
        return RetrofitInstance.api.getMovieReview()
    }

    fun getPagingMoviesFlow(): Flow<PagingData<Item>> {

        return Pager(PagingConfig(pageSize = 20)) {
            GithubPagingSource(RetrofitInstance.api)
        }.flow

    }

}