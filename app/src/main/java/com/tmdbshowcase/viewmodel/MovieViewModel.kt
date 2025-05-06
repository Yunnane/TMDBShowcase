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

    private val _movies = MutableLiveData<MovieResponse>()
    val movies: LiveData<MovieResponse> = _movies

    fun getPopularMovies(authHeader: String) {
        viewModelScope.launch {
            _movies.value = repository.getPopularMovies(authHeader)
        }
    }
}