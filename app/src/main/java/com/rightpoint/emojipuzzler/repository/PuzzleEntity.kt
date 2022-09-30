package com.rightpoint.emojipuzzler.repository

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Puzzle(@PrimaryKey(autoGenerate = true) val uid: Int,
                  @ColumnInfo(name = "message") val message: String,
                  @ColumnInfo(name = "solution" ) val solution: String)

class PuzzleEntity(val emojiPuzzles: List<Puzzle>) {

    var currentEmoji: Puzzle? = null

    init {
        pickNewEmoji()
    }

    fun pickNewEmoji() {
        if(emojiPuzzles.isEmpty()) {
            return
        }

        currentEmoji = emojiPuzzles[((Math.random() * emojiPuzzles.size)).toInt()]
    }

    fun compareWithCurrentEmoji(text: CharSequence): Boolean {
        return currentEmoji?.solution?.equals(text.toString(), true) ?: false
    }
}