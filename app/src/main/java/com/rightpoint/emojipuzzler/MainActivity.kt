package com.rightpoint.emojipuzzler

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.Text
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rightpoint.emojipuzzler.puzzle.Puzzle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavHost(rememberNavController(), startDestination = "puzzle") {
                composable("puzzle") { Puzzle(this@MainActivity) }
            }
        }
    }
}
