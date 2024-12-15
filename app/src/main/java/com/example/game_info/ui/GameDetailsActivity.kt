package com.example.game_info.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.game_info.R
import com.example.game_info.model.GameDetails
import com.example.game_info.network.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GameDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_details)

        // Get game ID from the intent
        val gameId = intent.getStringExtra("GAME_ID")

        // Find Views
        val gameImage: ImageView = findViewById(R.id.gameImage)
        val gameName: TextView = findViewById(R.id.nameValue)
        val gameType: TextView = findViewById(R.id.typeValue)
        val gameNbPlayers: TextView = findViewById(R.id.nbPlayersValue)
        val gameYear: TextView = findViewById(R.id.yearValue)
        val gameDescription: TextView = findViewById(R.id.gameDescription)
        val openBrowserButton: Button = findViewById(R.id.knowMoreButton)

        // Fetch game details using Retrofit
        RetrofitInstance.api.getGameDetails(gameId ?: "").enqueue(object : Callback<GameDetails> {
            override fun onResponse(call: Call<GameDetails>, response: Response<GameDetails>) {
                val gameDetails = response.body()
                if (gameDetails != null) {
                    // Update UI
                    gameName.text = gameDetails.name
                    gameType.text = gameDetails.type
                    gameNbPlayers.text = gameDetails.players.toString()
                    gameYear.text = gameDetails.year.toString()
                    gameDescription.text = gameDetails.description_en

                    Glide.with(this@GameDetailsActivity)
                        .load(gameDetails.picture)
                        .into(gameImage)

                    // Open browser on button click
                    openBrowserButton.setOnClickListener {
                        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(gameDetails.url))
                        startActivity(browserIntent)
                    }
                }
            }

            override fun onFailure(call: Call<GameDetails>, t: Throwable) {
                gameName.text = "Error loading game details."
            }
        })
    }
}