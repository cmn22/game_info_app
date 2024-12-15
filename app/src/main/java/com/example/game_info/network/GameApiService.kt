package com.example.game_info.network

import com.example.game_info.model.Game
import com.example.game_info.model.GameDetails
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GameApiService {
    @GET("game/list.json")
    fun getGameList(): Call<List<Game>>

    @GET("game/details{id}.json")
    fun getGameDetails(@Path("id") id: String): Call<GameDetails>
}