package com.example.twitch.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.twitch.R
import com.example.twitch.databinding.FragmentReviewBinding
import com.example.twitch.ui.states.FeedbackSendingResult
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class ReviewFragment : Fragment() {

    private lateinit var binding: FragmentReviewBinding
    private val viewModel: ReviewViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentReviewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()

        binding.sendReviewBtn.setOnClickListener {
            viewModel.sendFeedback(binding.ratingBar.rating, binding.reviewEt.text.toString())
        }

        viewModel.sendingResult.observe(viewLifecycleOwner, {
            when (it!!){
                FeedbackSendingResult.NOT_SEND -> {}
                FeedbackSendingResult.IN_PROGRESS -> {
                    binding.ratingBar.isEnabled = false
                    binding.reviewEt.isEnabled = false
                    binding.sendReviewBtn.visibility = View.GONE
                    binding.progressBar.visibility = View.VISIBLE
                }
                FeedbackSendingResult.SUCCESS -> {
                    Toast.makeText(context, getText(R.string.feedback_sent), Toast.LENGTH_SHORT).show()
                    findNavController().popBackStack()
                }
                FeedbackSendingResult.FAIL -> {
                    Toast.makeText(context, getText(R.string.feedback_fail), Toast.LENGTH_SHORT).show()
                }
            }

        })
    }
}