package com.rightpoint.repository

import com.google.gson.Gson
import com.rightpoint.repository.models.PuzzleEntity
import java.io.InputStreamReader

class PuzzleRepositoryClient: IRepositoryClient {

    private val gson = Gson()

    override suspend fun retrieveEmojiData(): PuzzleEntity {
        val input = javaClass.classLoader.getResourceAsStream("emojipuzzles.json")
        val reader = InputStreamReader(input, "UTF-8")
        return gson.fromJson(reader, PuzzleEntity::class.java)
    }
}