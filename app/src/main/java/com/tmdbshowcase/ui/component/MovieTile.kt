package com.tmdbshowcase.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.focusable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.tmdbshowcase.R
import com.tmdbshowcase.constants.Constants.IMAGE_URL
import com.tmdbshowcase.ui.model.Tile

@Composable
fun MovieTile(tile: Tile) {
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused = interactionSource.collectIsFocusedAsState()

    val fontSizeTitleSp = LocalDensity.current.run {
        (tile.size.width.value * 0.1f).sp
    }
    val fontSizeDetailsSp = LocalDensity.current.run {
        (tile.size.width.value * 0.05f).sp
    }
    val shape = RoundedCornerShape(8.dp)

    val votePercentage = (tile.movie.vote_average * 10).toInt()

    Box(
        modifier = Modifier
            .size(tile.size)
            .clip(shape)
            .border(
                width = 2.dp,
                color = if (isFocused.value) Color.Yellow else Color.Transparent,
                shape = shape
            )
            .focusable(interactionSource = interactionSource)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.DarkGray.copy(alpha = 0.4f))
        )
        AsyncImage(
            modifier = Modifier
                .fillMaxSize()
                .clip(shape),
            model = tile.movie.poster_path?.let { "$IMAGE_URL$it" },
            placeholder = painterResource(R.drawable.placeholder),
            error = painterResource(R.drawable.placeholder),
            contentDescription = tile.movie.title,
            contentScale = ContentScale.Crop
        )

        if (isFocused.value) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.9f))
                    .padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = tile.movie.title,
                    fontSize = fontSizeTitleSp,
                    fontWeight = FontWeight.Bold,
                    color = Color.LightGray,
                    overflow = TextOverflow.Clip,
                    textAlign = TextAlign.Center,
                    lineHeight = fontSizeTitleSp * 1.5
                )
                Text(
                    text = stringResource(R.string.rating, votePercentage),
                    fontSize = fontSizeDetailsSp,
                    color = Color.Gray,
                    maxLines = 1,
                    lineHeight = fontSizeDetailsSp * 1.5
                )
                Text(
                    text = stringResource(R.string.released_date, tile.movie.release_date),
                    fontSize = fontSizeDetailsSp,
                    color = Color.Gray,
                    maxLines = 1,
                    lineHeight = fontSizeDetailsSp * 1.5
                )
            }
        }
    }
}
