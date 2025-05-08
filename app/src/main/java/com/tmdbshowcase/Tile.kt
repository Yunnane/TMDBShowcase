package com.tmdbshowcase

import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.tmdbshowcase.data.model.Movie

sealed class Tile(val movie: Movie, val size: DpSize) {
    class Small(movie: Movie) :
        Tile(movie, DpSize(160.dp, 84.dp))

    class Medium(movie: Movie) :
        Tile(movie, DpSize(240.dp, 135.dp))

    class Large(movie: Movie) :
        Tile(movie, DpSize(320.dp, 180.dp))
}