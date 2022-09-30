package com.rightpoint.emojipuzzler.repository

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface PuzzleDao {
    @Query("SELECT * FROM puzzle")
    fun getAll(): Flow<List<Puzzle>>

    @Query("SELECT * FROM puzzle WHERE uid IN (:uIds)")
    fun loadAllByIds(uIds: IntArray): List<Puzzle>

    @Query("SELECT * FROM puzzle WHERE solution LIKE :solution LIMIT 1")
    fun findBySolution(solution: String): Puzzle

    @Insert
    fun insertAll(puzzle: List<Puzzle>)

    @Delete
    fun delete(puzzle: Puzzle)
}