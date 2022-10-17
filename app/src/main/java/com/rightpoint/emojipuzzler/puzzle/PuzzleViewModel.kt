package com.rightpoint.emojipuzzler.puzzle

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rightpoint.domain.IDomainClient
import com.rightpoint.emojipuzzler.EmojiPuzzleApplication
import com.rightpoint.emojipuzzler.repository.PuzzleEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PuzzleViewModel @Inject constructor() : ViewModel() {

    @Inject
    lateinit var domain: IDomainClient

    private var roomRepository = EmojiPuzzleApplication.roomRepositoryClient

    var puzzle: PuzzleEntity? = null

    var feedBackLiveData: MutableLiveData<String> = MutableLiveData("Press Start Game to begin")
    var currentEmojiPuzzle: MutableLiveData<String> = MutableLiveData()
    var loading: MutableLiveData<Boolean> = MutableLiveData()

    private val viewModelJob = Job()
    private val domainScope = CoroutineScope(Dispatchers.IO + viewModelJob)

    fun startGame() {
        loading.value = true
        domainScope.launch {
            roomRepository.initializeDatabase()
            feedBackLiveData.postValue("Enter your answer here or hit Start Game to start over.")
            loading.postValue(false)
            roomRepository.latestPuzzles.collectLatest {
                puzzle = it
                currentEmojiPuzzle.postValue(puzzle?.currentEmoji?.message)
            }
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

    fun updateDatabase() {
        domainScope.launch {
            roomRepository.updateDatabase()
        }
    }
}