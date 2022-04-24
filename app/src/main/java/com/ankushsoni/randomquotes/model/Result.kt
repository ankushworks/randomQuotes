package com.ankushsoni.randomquotes.model

import com.google.gson.annotations.SerializedName


data class Results (

    @SerializedName("tags"         ) var tags         : ArrayList<String> = arrayListOf(),
    @SerializedName("_id"          ) var Id           : String?           = null,
    @SerializedName("content"      ) var content      : String?           = null,
    @SerializedName("author"       ) var author       : String?           = null,
    @SerializedName("authorSlug"   ) var authorSlug   : String?           = null,
    @SerializedName("length"       ) var length       : Int?              = null,
    @SerializedName("dateAdded"    ) var dateAdded    : String?           = null,
    @SerializedName("dateModified" ) var dateModified : String?           = null

)