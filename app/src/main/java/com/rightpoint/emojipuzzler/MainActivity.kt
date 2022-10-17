package com.rightpoint.emojipuzzler

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rightpoint.emojipuzzler.puzzle.Puzzle
import com.rightpoint.emojipuzzler.puzzle.PuzzleViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: PuzzleViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(application).create(
            PuzzleViewModel::class.java)

        setContent {
            NavHost(rememberNavController(), startDestination = "puzzle") {
                composable("puzzle") { Puzzle(this@MainActivity, viewModel) }
            }
        }
    }
}
