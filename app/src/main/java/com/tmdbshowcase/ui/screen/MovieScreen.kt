package com.tmdbshowcase.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.tmdbshowcase.BuildConfig
import com.tmdbshowcase.R
import com.tmdbshowcase.ui.component.EmptyApiKeyText
import com.tmdbshowcase.ui.component.MovieRow
import com.tmdbshowcase.ui.component.PlaceholderBox
import com.tmdbshowcase.ui.component.ScreenTitle
import com.tmdbshowcase.ui.model.Tile
import com.tmdbshowcase.viewmodel.MovieViewModel

@Composable
fun MovieScreen(viewModel: MovieViewModel) {
    val apiKey = BuildConfig.TMDB_API_KEY

    val popularMovies by viewModel.popularMovies.observeAsState()
    val topRatedMovies by viewModel.topRatedMovies.observeAsState()
    val upcomingMovies by viewModel.upcomingMovies.observeAsState()

    if (!apiKey.isNullOrEmpty()) {
        LaunchedEffect(Unit) {
            viewModel.getPopularMovies(apiKey)
            viewModel.getTopRatedMovies(apiKey)
            viewModel.getUpcomingMovies(apiKey)
        }
        if (popularMovies == null || topRatedMovies == null || upcomingMovies == null) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = Color.White)
            }
        } else {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black)
                        .padding(horizontal = 32.dp, vertical = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    item {
                        ScreenTitle()
                    }

                    upcomingMovies?.let {
                        item {
                            MovieRow(rowTitle = stringResource(R.string.upcoming_movies),
                                it.results.take(10).map { Tile.Small(it) })
                        }
                    } ?: item {
                        PlaceholderBox(title = stringResource(R.string.upcoming_movies))
                    }

                    topRatedMovies?.let {
                        item {
                            MovieRow(
                                rowTitle = stringResource(R.string.top_rated_movies),
                                it.results.take(10).map { Tile.Medium(it) })
                        }
                    } ?: item {
                        PlaceholderBox(title = stringResource(R.string.top_rated_movies))
                    }

                    popularMovies?.let {
                        item {
                            MovieRow(
                                rowTitle = stringResource(R.string.popular_movies),
                                it.results.take(10).map { Tile.Large(it) })
                        }
                    } ?: item {
                        PlaceholderBox(title = stringResource(R.string.popular_movies))
                    }
                }
            }
        }
    } else {
        EmptyApiKeyText()
    }
}

