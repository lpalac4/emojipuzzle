package com.rightpoint.emojipuzzler.repository

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.google.gson.Gson
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import java.io.InputStreamReader

class RoomRepositoryClient(var applicationContext: Context) {

    private val gson = Gson()
    private lateinit var db: PuzzleDatabase

    lateinit var latestPuzzles: Flow<PuzzleEntity>

    fun initializeDatabase() {
        db = Room.databaseBuilder(applicationContext, PuzzleDatabase::class.java, "puzzle-database").build()
        val input = javaClass.classLoader?.getResourceAsStream("emojipuzzles.json")
        val reader = InputStreamReader(input, "UTF-8")
        val puzzleEntity = gson.fromJson(reader, PuzzleEntity::class.java)
        db.puzzleDao().insertAll(puzzleEntity.emojiPuzzles)
        latestPuzzles = db.puzzleDao().getAll().map { PuzzleEntity(it) }
    }

    suspend fun updateDatabase() {
        db.puzzleDao().getAll().collect { puzzles ->
            var count = puzzles.size
            puzzles.forEach {
                if ( count > 1) db.puzzleDao().delete(it)
                count -= 1
            }
        }
    }
}