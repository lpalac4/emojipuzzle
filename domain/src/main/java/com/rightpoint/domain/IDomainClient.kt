package com.rightpoint.domain

import com.rightpoint.domain.models.EmojiPuzzle
import com.rightpoint.repository.IRepositoryClient

interface IDomainClient {

    val repository: IRepositoryClient

    suspend fun getPuzzles() : EmojiPuzzle
}
