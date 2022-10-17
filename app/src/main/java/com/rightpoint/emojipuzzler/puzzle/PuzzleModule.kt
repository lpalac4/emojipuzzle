package com.rightpoint.emojipuzzler.puzzle

import com.rightpoint.domain.IDomainClient
import com.rightpoint.domain.PuzzleDomainClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object PuzzleModule {
    @Provides
    fun provideRepositoryClient() : IDomainClient {
        return PuzzleDomainClient()
    }
}