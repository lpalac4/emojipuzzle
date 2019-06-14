package com.rightpoint.repository

import com.rightpoint.repository.models.PuzzleEntity

interface IRepositoryClient {

    suspend fun retrieveEmojiData(): PuzzleEntity
}
