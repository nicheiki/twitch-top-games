package com.example.twitch.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.children
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.twitch.databinding.FragmentListBinding
import com.example.twitch.di.TwitchApplication
import com.example.twitch.ui.adapters.GamesAdapter
import com.example.twitch.ui.adapters.GamesLoadStateAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ListFragment() : Fragment() {

    private lateinit var binding: FragmentListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(inflater, container, false)

        val viewModel: ListViewModel by viewModels { ListViewModelFactory(requireActivity().application as TwitchApplication) }

        //Adapters
        val gamesAdapter = GamesAdapter()
        binding.gamesRv.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = gamesAdapter.withLoadStateHeaderAndFooter(
                header = GamesLoadStateAdapter { gamesAdapter.retry() },
                footer = GamesLoadStateAdapter { gamesAdapter.retry() }
            )
            setHasFixedSize(true)
        }

        binding.retryButton.setOnClickListener {
            gamesAdapter.retry()
        }




        gamesAdapter.addLoadStateListener {
            binding.listProgressBar.isVisible = it.source.refresh is LoadState.Loading
            binding.retryButton.isVisible = it.source.refresh is LoadState.Error
        }

        lifecycleScope.launch {
            viewModel.getGames().collectLatest {
                gamesAdapter.submitData(it)
            }
        }

        return binding.root
    }
}