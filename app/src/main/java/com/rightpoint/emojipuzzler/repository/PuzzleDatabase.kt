package com.rightpoint.emojipuzzler.repository

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Puzzle::class], version = 1)
abstract class PuzzleDatabase: RoomDatabase() {
    abstract fun puzzleDao(): PuzzleDao
}