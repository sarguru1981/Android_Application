package com.poc.data.room.post

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PostKey(
    @PrimaryKey(autoGenerate = false)
    var id: String,
    var prev: Int?,
    var next: Int?
)
