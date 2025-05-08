package com.tmdbshowcase.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.focusable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.tmdbshowcase.R
import com.tmdbshowcase.Tile
import com.tmdbshowcase.constants.Constants.IMAGE_URL

@Composable
fun MovieTile(tile: Tile) {
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused = interactionSource.collectIsFocusedAsState()

    val fontSizeTitleSp = LocalDensity.current.run {
        (tile.size.width.value * 0.05f).sp
    }
    val fontSizeDetailsSp = LocalDensity.current.run {
        (tile.size.width.value * 0.04f).sp
    }
    val shape = RoundedCornerShape(12.dp)

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
            placeholder = painterResource(R.drawable.image),
            error = painterResource(R.drawable.image),
            contentDescription = tile.movie.title,
            contentScale = ContentScale.Fit
        )

        if (isFocused.value) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Black.copy(alpha = 0.9f))
                    .align(Alignment.BottomStart)
                    .padding(vertical = 2.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = tile.movie.title,
                        fontSize = fontSizeTitleSp,
                        fontWeight = FontWeight.Bold,
                        color = Color.LightGray,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        lineHeight = fontSizeTitleSp * 1.3
                    )
                    Text(
                        text = "Rating: $votePercentage%",
                        fontSize = fontSizeDetailsSp,
                        color = Color.Gray,
                        maxLines = 1,
                        lineHeight = fontSizeDetailsSp * 1.3
                    )
                    Text(
                        text = "Released: ${tile.movie.release_date}",
                        fontSize = fontSizeDetailsSp,
                        color = Color.Gray,
                        maxLines = 1,
                        lineHeight = fontSizeDetailsSp * 1.3
                    )
                }
            }
        }
    }
}
