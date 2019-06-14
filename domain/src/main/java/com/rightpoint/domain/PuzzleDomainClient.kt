package com.rightpoint.domain

import com.rightpoint.domain.models.EmojiPuzzle
import com.rightpoint.repository.IRepositoryClient
import com.rightpoint.repository.PuzzleRepositoryClient

class PuzzleDomainClient(override val repository: IRepositoryClient = PuzzleRepositoryClient()) : IDomainClient {

    override suspend fun getPuzzles(): EmojiPuzzle {
        return EmojiPuzzle(repository.retrieveEmojiData())
    }
}