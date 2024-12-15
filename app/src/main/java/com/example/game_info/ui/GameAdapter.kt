package com.example.game_info.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.game_info.R
import com.example.game_info.model.Game

class GameAdapter(
    private val games: List<Game>,
    private val onClick: (Game) -> Unit
) : RecyclerView.Adapter<GameAdapter.GameViewHolder>() {

    class GameViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.gameName)
        val image: ImageView = view.findViewById(R.id.gameImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_game, parent, false)
        return GameViewHolder(view)
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        val game = games[position]
        holder.name.text = game.name

        // Load the image using Glide
        Glide.with(holder.itemView.context)
            .load(game.picture) // URL of the image
            .into(holder.image)

        holder.itemView.setOnClickListener { onClick(game) }
    }

    override fun getItemCount() = games.size
}