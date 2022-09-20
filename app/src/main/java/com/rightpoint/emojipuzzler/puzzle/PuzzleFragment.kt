package com.rightpoint.emojipuzzler.puzzle

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.rightpoint.emojipuzzler.databinding.PuzzleFragmentBinding

class PuzzleFragment : Fragment() {

    private lateinit var viewModel: PuzzleViewModel
    private var _binding: PuzzleFragmentBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application).create(PuzzleViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = PuzzleFragmentBinding.inflate(inflater, container, false)
        Log.d(PuzzleFragment::class.simpleName, "binding is null\n$_binding")

        viewModel.currentEmojiPuzzle.observe(viewLifecycleOwner, Observer {
            _binding?.emojiContainer?.text = it
        })
        viewModel.feedBackLiveData.observe(viewLifecycleOwner, Observer {
            _binding?.activeFeedback?.text = it
        })
        viewModel.loading.observe(viewLifecycleOwner, Observer {
            _binding?.progressBar?.visibility = if(it) View.VISIBLE else View.GONE
        })
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding?.idleStartButton?.setOnClickListener {
            _binding?.idleContainer?.visibility = View.GONE
            _binding?.activeContainer?.visibility = View.VISIBLE
            viewModel.startGame()
        }

        _binding?.emojiContainer?.setOnClickListener {
            viewModel.newEmoji()
        }

        _binding?.activeAnswer?.editText?.setOnEditorActionListener { textView, actionId, event ->
            if (actionId == EditorInfo.IME_NULL
                && event.action == KeyEvent.ACTION_DOWN) {
                Log.v("EmojiEntry", textView.text.toString())
                viewModel.checkAnswer(textView.text)
            }

            true
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}