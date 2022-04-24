package com.ankushsoni.randomquotes.model

import com.google.gson.annotations.SerializedName

data class TestingQuotesList(
    @SerializedName("count"         ) var count         : Int?               = null,
    @SerializedName("totalCount"    ) var totalCount    : Int?               = null,
    @SerializedName("page"          ) var page          : Int?               = null,
    @SerializedName("totalPages"    ) var totalPages    : Int?               = null,
    @SerializedName("lastItemIndex" ) var lastItemIndex : String?            = null,
    @SerializedName("results"       ) var results       : ArrayList<Results> = arrayListOf()
)