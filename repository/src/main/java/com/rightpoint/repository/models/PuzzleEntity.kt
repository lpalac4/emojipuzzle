package com.rightpoint.repository.models

data class PuzzleEntity(val emojiPuzzles: List<Puzzle>) {

    data class Puzzle(val message: String, val solution: String)
}