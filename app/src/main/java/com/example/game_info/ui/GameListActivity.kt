package com.example.game_info.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.game_info.R
import com.example.game_info.model.Game
import com.example.game_info.network.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GameListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_list)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        RetrofitInstance.api.getGameList().enqueue(object : Callback<List<Game>> {
            override fun onResponse(call: Call<List<Game>>, response: Response<List<Game>>) {
                val gameList = response.body()?.shuffled()?.take(3)
                recyclerView.adapter = GameAdapter(gameList ?: emptyList()) { game ->
                    val intent = Intent(this@GameListActivity, GameDetailsActivity::class.java)
                    intent.putExtra("GAME_ID", game.id)
                    startActivity(intent)
                }
            }

            override fun onFailure(call: Call<List<Game>>, t: Throwable) {
                Toast.makeText(this@GameListActivity, "Error fetching games", Toast.LENGTH_SHORT).show()
            }
        })
    }
}