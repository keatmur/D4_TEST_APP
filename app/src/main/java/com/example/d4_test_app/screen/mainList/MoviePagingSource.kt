package com.example.d4_test_app.screen.mainList

// MoviePagingSource.kt
import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.d4_test_app.remote.MovieApi
import com.example.d4_test_app.remote.response.Movie
import timber.log.Timber
import javax.inject.Inject

class MoviePagingSource@Inject internal constructor(
    private val webService: MovieApi) : PagingSource<Int, Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val currentPage = params.key ?: 1
            val response = webService.getMovies(page=currentPage)
            val movies = response.results

            LoadResult.Page(
                data = movies,
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = if (movies.isEmpty()) null else currentPage + 1
            )
        } catch (e: Exception) {
            Timber.e("D4_test",e.message.toString())
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition
    }
}
