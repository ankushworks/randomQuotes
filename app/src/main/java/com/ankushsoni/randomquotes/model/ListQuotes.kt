package com.ankushsoni.randomquotes.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "ListQuotes" , indices = [Index(value = ["quotes_content"], unique = true)])
data class ListQuotes(
    @PrimaryKey(autoGenerate = true) val id : Int,
    val quotes_content : String,
    val quotes_author : String,
)