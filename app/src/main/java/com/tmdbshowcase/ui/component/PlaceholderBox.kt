package com.tmdbshowcase.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import com.tmdbshowcase.R

@Composable
fun PlaceholderBox(title: String) {
    Box(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = stringResource(R.string.placeholder_text, title),
            modifier = Modifier.align(Alignment.Center),
            fontSize = 16.sp,
            color = Color.LightGray
        )
    }
}