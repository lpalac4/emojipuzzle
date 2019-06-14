package com.rightpoint.domain.models

import com.rightpoint.repository.models.PuzzleEntity

data class EmojiPuzzle(val entity: PuzzleEntity) {

    var currentEmoji: PuzzleEntity.Puzzle? = null

    init {
        pickNewEmoji()
    }

    fun pickNewEmoji() {
        if(entity.emojiPuzzles.isEmpty()) {
            return
        }

        currentEmoji = entity.emojiPuzzles[((Math.random() * entity.emojiPuzzles.size)).toInt()]
    }

    fun compareWithCurrentEmoji(text: CharSequence): Boolean {
        return currentEmoji?.solution?.equals(text.toString(), true) ?: false
    }
}