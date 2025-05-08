package com.tmdbshowcase.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.focusable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.tmdbshowcase.R

@Composable
fun ScreenTitle() {
    val interactionSource = remember { MutableInteractionSource() }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .focusable(interactionSource = interactionSource),
        contentAlignment = Alignment.Center
    )
    {
        Image(
            painter = painterResource(R.drawable.tmdbshowcaseapp),
            contentDescription = stringResource(R.string.screen_title),
            modifier = Modifier.height(80.dp)
        )
    }
}