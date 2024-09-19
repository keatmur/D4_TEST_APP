package com.example.d4_test_app.screen.mainList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.d4_test_app.remote.repo.MovieRepositoryImpl
import com.example.d4_test_app.remote.response.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.forEach
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val movieRepository: MovieRepositoryImpl): ViewModel() {
    val movies: Flow<PagingData<Movie>> =movieRepository.getMovies()
        .cachedIn(viewModelScope)

}