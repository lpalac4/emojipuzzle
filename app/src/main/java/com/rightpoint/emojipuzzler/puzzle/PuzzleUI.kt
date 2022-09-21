package com.rightpoint.emojipuzzler.puzzle

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun Puzzle(context: Context) {
    Box(modifier = Modifier.fillMaxSize()) {
        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            var text by remember { mutableStateOf("Input") }
            Text("\uD83E\uDD70 \uD83E\uDD70 \uD83E\uDD70 \uD83E\uDD70")
            TextField(
                value = text,
                onValueChange = { text = it },
                label = { Text("Enter your answer") },
                maxLines = 1
            )
        }
    }
}