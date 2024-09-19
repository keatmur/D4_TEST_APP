package com.example.d4_test_app.remote.repo

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.filter
import com.example.d4_test_app.screen.mainList.MoviePagingSource
import com.example.d4_test_app.remote.MovieApi
import com.example.d4_test_app.remote.response.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import javax.inject.Inject


class MovieRepositoryImpl @Inject internal constructor(
    private val webService: MovieApi) {
    private lateinit var moviesShow :Flow<PagingData<Movie>>
    fun getMovies(): Flow<PagingData<Movie>> {
        moviesShow=Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = false),
            pagingSourceFactory = { MoviePagingSource(webService) }
        ).flow
        return moviesShow
    }

}