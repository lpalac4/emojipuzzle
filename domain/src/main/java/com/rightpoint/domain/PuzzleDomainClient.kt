package com.rightpoint.domain

import com.rightpoint.domain.models.EmojiPuzzle
import com.rightpoint.repository.IRepositoryClient
import com.rightpoint.repository.PuzzleRepositoryClient

class PuzzleDomainClient(override val repository: IRepositoryClient = PuzzleRepositoryClient()) : IDomainClient {

    val repo = PuzzleRepositoryClient()
    override suspend fun getPuzzles(): EmojiPuzzle {
        return EmojiPuzzle(repo.retrieveEmojiData())
    }
}