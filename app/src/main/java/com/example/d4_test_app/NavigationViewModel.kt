package com.example.d4_test_app

import androidx.lifecycle.ViewModel
import com.example.d4_test_app.remote.response.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class NavigationViewModel @Inject constructor() : ViewModel() {
    private val _selectedMovie: MutableStateFlow<Movie?> = MutableStateFlow(null)
    val selectedMovie: StateFlow<Movie?> = _selectedMovie
    fun setSelectedMovie(movie: Movie) {
        _selectedMovie.value=movie
    }
}