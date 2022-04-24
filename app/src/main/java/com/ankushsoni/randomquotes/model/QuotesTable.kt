package com.ankushsoni.randomquotes.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity(tableName = "Quotes" , indices = [Index(value = ["quote"], unique = true)])
data class QuotesTable(
    @PrimaryKey(autoGenerate = true)
    var id : Long,
    val quote : String,
    var author : String,
)
