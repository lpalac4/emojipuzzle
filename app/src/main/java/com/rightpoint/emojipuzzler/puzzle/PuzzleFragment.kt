package com.rightpoint.emojipuzzler.puzzle

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.rightpoint.emojipuzzler.R
import kotlinx.android.synthetic.main.puzzle_fragment.*

class PuzzleFragment : Fragment() {

    private lateinit var viewModel: PuzzleViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(activity!!.application).create(PuzzleViewModel::class.java)

        viewModel.currentEmojiPuzzle.observe(this, Observer {
            emoji_container.text = it
        })
        viewModel.feedBackLiveData.observe(this, Observer {
            active_feedback.text = it
        })
        viewModel.loading.observe(this, Observer {
            progress_bar.visibility = if(it) View.VISIBLE else View.GONE
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.puzzle_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        idle_start_button.setOnClickListener {
            idle_container.visibility = View.GONE
            active_container.visibility = View.VISIBLE
            viewModel.startGame()
        }

        emoji_container.setOnClickListener {
            viewModel.newEmoji()
        }

        active_answer.editText?.setOnEditorActionListener { textView, actionId, event ->
            if (actionId == EditorInfo.IME_NULL
                && event.action == KeyEvent.ACTION_DOWN) {
                viewModel.checkAnswer(textView.text)
            }

            true
        }
    }
}