package com.noob.apps.mvpretrofit.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RestClient {
    companion object {
        private val mInstance: RestClient = RestClient()
        private val BASE_URL: String = "https://restcountries.eu/rest/v2/"
        public fun getInstance() = mInstance
    }

    private lateinit var mApi: NetworkApi

    init {
        var okHttpClient: OkHttpClient =
            OkHttpClient().newBuilder().connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .build();
        var retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
        mApi = retrofit.create(NetworkApi::class.java)
    }

    public fun getNetworkService() = mApi
}