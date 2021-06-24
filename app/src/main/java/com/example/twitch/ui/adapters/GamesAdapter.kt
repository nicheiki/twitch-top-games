package com.example.twitch.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.twitch.data.database.Game
import com.example.twitch.databinding.GameItemBinding
import com.squareup.picasso.Picasso

class GamesAdapter : PagingDataAdapter<Game, GamesAdapter.GameViewHolder>(COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val binding = GameItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GameViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }


    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<Game>() {

            override fun areItemsTheSame(oldItem: Game, newItem: Game) =
                oldItem.name == newItem.name

            override fun areContentsTheSame(oldItem: Game, newItem: Game): Boolean =
                oldItem == newItem
        }
    }

    class GameViewHolder(private val item: GameItemBinding) : RecyclerView.ViewHolder(item.root) {


        fun bind(game: Game) {
            item.apply {
                name.text = game.name
                channels.text = game.channels.toString()
                viewers.text = game.viewers.toString()
                Picasso.get().load(game.image).into(screen)

            }
        }
    }

}

