package com.codeliner.moviestutu.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.codeliner.moviestutu.data.retrofit.api.ApiService
import com.codeliner.moviestutu.model.Item
import retrofit2.HttpException
import java.io.IOException

@Suppress("UNREACHABLE_CODE")
class MoviesPagingSource(
    private val apiService: ApiService
) : PagingSource<Int, Item>() {

    companion object {
        private const val MOVIES_STARTING_PAGE_OFFSET = 0
    }

    override fun getRefreshKey(state: PagingState<Int, Item>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(20)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(20)
        }

        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition) ?: return null
        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    } //загрузить текущие данные при обновлении списка

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Item> {

        val offset = params.key ?: MOVIES_STARTING_PAGE_OFFSET
        return try {
            val response = apiService.getMovieReview() //загрузить данные
            val movies = response.items
            val nextOffset = if (movies.isEmpty()) {
                null
            } else {
                offset + 20
            } //вычислить некст ки
            LoadResult.Page(
                data = movies,
                prevKey = null,
                nextKey = nextOffset
            ) //вернуть пейдж
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }
}