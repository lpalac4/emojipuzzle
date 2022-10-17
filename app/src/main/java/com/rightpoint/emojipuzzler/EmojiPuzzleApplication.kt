package com.rightpoint.emojipuzzler

import android.app.Application
import androidx.emoji.bundled.BundledEmojiCompatConfig
import androidx.emoji.text.EmojiCompat
import com.rightpoint.domain.IDomainClient
import com.rightpoint.domain.PuzzleDomainClient
import com.rightpoint.emojipuzzler.repository.RoomRepositoryClient
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class EmojiPuzzleApplication: Application(){

    override fun onCreate() {
        super.onCreate()

        val config = BundledEmojiCompatConfig(this)
        EmojiCompat.init(config)
        roomRepositoryClient = RoomRepositoryClient(this)
    }

    companion object {
        lateinit var roomRepositoryClient: RoomRepositoryClient
    }
}