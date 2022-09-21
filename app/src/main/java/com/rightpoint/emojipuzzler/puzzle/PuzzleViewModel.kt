package com.rightpoint.emojipuzzler.puzzle

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rightpoint.domain.IDomainClient
import com.rightpoint.domain.models.EmojiPuzzle
import com.rightpoint.emojipuzzler.EmojiPuzzleApplication
import kotlinx.coroutines.*

class PuzzleViewModel: ViewModel() {

    private var domain: IDomainClient = EmojiPuzzleApplication.domainClient

    var puzzle: EmojiPuzzle? = null

    var feedBackLiveData: MutableLiveData<String> = MutableLiveData("Press Start Game to begin")
    var currentEmojiPuzzle: MutableLiveData<String> = MutableLiveData()
    var loading: MutableLiveData<Boolean> = MutableLiveData()

    private val viewModelJob = Job()
    private val domainScope = CoroutineScope(Dispatchers.IO + viewModelJob)

    fun startGame() {
        loading.value = true
        domainScope.launch {
            delay(4000)
            puzzle = domain.getPuzzles()
            currentEmojiPuzzle.postValue(puzzle?.currentEmoji?.message)
            feedBackLiveData.postValue("Enter your answer here")
            loading.postValue(false)
        }
    }

    fun newEmoji() {
        puzzle?.pickNewEmoji()
        currentEmojiPuzzle.value = puzzle?.currentEmoji?.message
    }

    fun checkAnswer(text: CharSequence) {
        val solution = puzzle?.compareWithCurrentEmoji(text)
        if(solution != null && solution) {
            feedBackLiveData.value = "That's right"
        } else {
            feedBackLiveData.value = "Nope try again. Hint: They're Drake Songs"
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}