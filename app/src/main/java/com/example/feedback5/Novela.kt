package com.example.feedback5

data class Novela(
    val id: Int,
    val nombre: String,
    val autor: String,
    val fecha: String,
    val sinopsis: String,
    var esFavorita: Boolean = false
)
