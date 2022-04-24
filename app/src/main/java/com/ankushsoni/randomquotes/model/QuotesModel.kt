package com.ankushsoni.randomquotes.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


data class QuotesModel (


    @SerializedName("_id"          ) var Id : String? = null,
    @SerializedName("tags"         ) var tags         : ArrayList<String> = arrayListOf(),
    @SerializedName("content"      ) var content      : String?           = null,
    @SerializedName("author"       ) var author       : String?           = null,
    @SerializedName("authorSlug"   ) var authorSlug   : String?           = null,
    @SerializedName("length"       ) var length       : Int?              = null,
    @SerializedName("dateAdded"    ) var dateAdded    : String?           = null,
    @SerializedName("dateModified" ) var dateModified : String?           = null

)
