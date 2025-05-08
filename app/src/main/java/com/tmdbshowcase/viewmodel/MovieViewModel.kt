package com.tmdbshowcase.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tmdbshowcase.data.api.RetrofitInstance
import com.tmdbshowcase.data.model.MovieResponse
import com.tmdbshowcase.data.repository.MovieRepository
import kotlinx.coroutines.launch

class MovieViewModel : ViewModel() {
    private val repository = MovieRepository(RetrofitInstance.tmdbApi)

    private val _popularMovies = MutableLiveData<MovieResponse>()
    val popularMovies: LiveData<MovieResponse> = _popularMovies

    private val _topRatedMovies = MutableLiveData<MovieResponse>()
    val topRatedMovies: LiveData<MovieResponse> = _topRatedMovies

    private val _upcomingMovies = MutableLiveData<MovieResponse>()
    val upcomingMovies: LiveData<MovieResponse> = _upcomingMovies

    fun getPopularMovies(authHeader: String) {
        viewModelScope.launch {
            _popularMovies.value = repository.getPopularMovies(authHeader)
        }
    }

    fun getTopRatedMovies(authHeader: String) {
        viewModelScope.launch {
            _topRatedMovies.value = repository.getTopRatedMovies(authHeader)
        }
    }

    fun getUpcomingMovies(authHeader: String) {
        viewModelScope.launch {
            _upcomingMovies.value = repository.getUpcomingMovies(authHeader)
        }
    }
}