package com.rightpoint.emojipuzzler.puzzle

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Puzzle(context: Context, viewModel: PuzzleViewModel) {

    Box(modifier = Modifier.fillMaxSize()) {
        val processing by viewModel.loading.observeAsState()
        val feedback by viewModel.feedBackLiveData.observeAsState()
        val puzzleEmoji by viewModel.currentEmojiPuzzle.observeAsState()

        if(processing == true) CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        Column(
            modifier = Modifier.fillMaxSize().padding(vertical = 10.dp).clickable { viewModel.newEmoji() },
            verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(puzzleEmoji ?: "")
            TextField(
                value = "",
                onValueChange = { viewModel.checkAnswer(it) },
                label = { Text(feedback ?: "N/A") },
                maxLines = 1
            )
            Button(onClick = { viewModel.startGame() }) {
                Text("Start Game")
            }
        }
    }
}