package com.davydikes.rickandmorty.api

import com.google.gson.annotations.SerializedName

class RickAndMortyCharecter (
    val name: String,
    val image: String,
    val status: String,
    val species: String,
    val gender: String
)

class PageCharacter(
    @SerializedName("info")
    val info : Info,
    @SerializedName("results")
    val result : List<RickAndMortyCharecter>
)

class Info(
    val count: Int,
    val pages: Int,
    val nextPage: String,
    val prevPage: String
)