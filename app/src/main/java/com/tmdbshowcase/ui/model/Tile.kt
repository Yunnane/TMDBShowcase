package com.tmdbshowcase.ui.model

import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.tmdbshowcase.data.model.Movie

sealed class Tile(val movie: Movie, val size: DpSize) {
    class Small(movie: Movie) :
        Tile(movie, DpSize(100.dp, 180.dp))

    class Medium(movie: Movie) :
        Tile(movie, DpSize(135.dp, 240.dp))

    class Large(movie: Movie) :
        Tile(movie, DpSize(180.dp, 320.dp))
}