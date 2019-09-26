package com.noob.apps.mvpretrofit.models

import com.google.gson.annotations.SerializedName

data class Country(
    @SerializedName("name") val name: String,
    @SerializedName("capital") val capital: String,
    @SerializedName("region") val region: String,
    @SerializedName("population") val population: Int,
    @SerializedName("currencies") val currencies: List<Currencies>,
    @SerializedName("languages") val languages: List<Languages>,
    @SerializedName("flag") val flag: String
)