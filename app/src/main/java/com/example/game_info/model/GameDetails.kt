package com.example.game_info.model

data class GameDetails(
    val id: String,
    val picture: String,
    val name: String,
    val type: String,
    val players: Int,
    val year: Int,
    val description_en: String,
    val url: String
)
