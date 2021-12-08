package com.example.workforfirstsem.model

import androidx.annotation.DrawableRes
import androidx.annotation.RawRes

data class Song(
    val id: Int,
    val title: String,
    @DrawableRes val cover: Int,
    @RawRes val song: Int,
    val singer: String,
    val duration: String,
)
