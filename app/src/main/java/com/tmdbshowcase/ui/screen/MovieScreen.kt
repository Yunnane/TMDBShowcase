package com.tmdbshowcase.ui.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tmdbshowcase.ui.component.MovieRow
import com.tmdbshowcase.viewmodel.MovieViewModel

@Composable
fun MovieScreen(viewModel: MovieViewModel) {
    val movieResponse by viewModel.popularMovies.observeAsState()

    LaunchedEffect(Unit) {
        viewModel.getPopularMovies("")
    }

    movieResponse?.let {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            item {
                Text(
                    "Popular videos (size small):",
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier.padding(16.dp)
                )
                MovieRow(movieResponse!!.results)
            }

            item {
                Text(
                    "Popular videos (size medium):",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(16.dp)
                )
                MovieRow(movieResponse!!.results)
            }

            item {
                Text(
                    "Popular videos (size large):",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(16.dp)
                )
                MovieRow(movieResponse!!.results)
            }
        }
    } ?: CircularProgressIndicator()
}