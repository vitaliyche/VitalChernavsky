package com.codeliner.vitalchernavsky.view.movies

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.codeliner.vitalchernavsky.data.retrofit.RetrofitRepository
import com.codeliner.vitalchernavsky.model.GitHubSearch
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MoviesViewModel : ViewModel() {
    private val repository = RetrofitRepository()
    val myMovies: MutableLiveData<GitHubSearch> = MutableLiveData()
    val pagingMoviesFlow = repository.getPagingMoviesFlow()
        .stateIn(
            viewModelScope,
            SharingStarted.Lazily,
            PagingData.empty()
        ) //когда нет данных, класть эмпти

    fun getMovies() {
        viewModelScope.launch {
            runCatching {
                myMovies.value = repository.getMovies()
            }
                .onFailure {
                    Log.e("ERROR", it.message.toString())
                }
        }
    }

}