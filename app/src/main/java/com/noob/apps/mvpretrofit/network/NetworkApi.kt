package com.noob.apps.mvpretrofit.network

import com.noob.apps.mvpretrofit.models.Country
import retrofit2.Call
import retrofit2.http.GET

interface NetworkApi {
    @GET("all")
    fun getCountriesFromNetwork(): Call<List<Country>>
}