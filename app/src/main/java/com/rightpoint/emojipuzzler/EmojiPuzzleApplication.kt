package com.rightpoint.emojipuzzler

import android.app.Application
import androidx.emoji.bundled.BundledEmojiCompatConfig
import androidx.emoji.text.EmojiCompat
import com.rightpoint.domain.IDomainClient
import com.rightpoint.domain.PuzzleDomainClient
import com.rightpoint.emojipuzzler.repository.RoomRepositoryClient

class EmojiPuzzleApplication: Application(){

    override fun onCreate() {
        super.onCreate()

        val config = BundledEmojiCompatConfig(this)
        EmojiCompat.init(config)
        roomRepositoryClient = RoomRepositoryClient(this)
    }

    companion object {
        var domainClient: IDomainClient = PuzzleDomainClient()
        lateinit var roomRepositoryClient: RoomRepositoryClient
    }
}