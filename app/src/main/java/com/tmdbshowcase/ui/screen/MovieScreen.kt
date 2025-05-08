package com.tmdbshowcase.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tmdbshowcase.BuildConfig
import com.tmdbshowcase.Tile
import com.tmdbshowcase.ui.component.MovieRow
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

        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black)
                    .padding(horizontal = 32.dp, vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                popularMovies?.let {
                    item {
                        MovieRow("Popular movies", it.results.take(10).map { Tile.Large(it) })
                    }
                }

                upcomingMovies?.let {
                    item {
                        MovieRow("Upcoming", it.results.take(10).map { Tile.Small(it) })
                    }
                }

                topRatedMovies?.let {
                    item {
                        MovieRow("Top rated", it.results.take(10).map { Tile.Medium(it) })
                    }
                }
            }
        }
    } else {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(
                text = "Error loading data. Please check application configuration.",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.LightGray
            )
        }
    }
}
