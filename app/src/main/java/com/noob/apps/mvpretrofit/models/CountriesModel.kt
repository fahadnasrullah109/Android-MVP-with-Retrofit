package com.noob.apps.mvpretrofit.models

import com.noob.apps.mvpretrofit.interfaces.MainContract
import com.noob.apps.mvpretrofit.network.NetworkApi
import com.noob.apps.mvpretrofit.network.RestClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CountriesModel : MainContract.Model, Callback<List<Country>> {
    private lateinit var mListener :MainContract.Model.OnFinishListener
    private lateinit var mCountriesCall: Call<List<Country>>

    override fun loadCountries(listener: MainContract.Model.OnFinishListener) {
        mListener = listener
        val apiNetwork = RestClient.getInstance().getNetworkService()
        mCountriesCall = apiNetwork.getCountriesFromNetwork()
        mCountriesCall.enqueue(this)
    }

    override fun onFailure(call: Call<List<Country>>, t: Throwable) {
        mListener.onFinishFailure(t)
    }

    override fun onResponse(call: Call<List<Country>>, response: Response<List<Country>>) {
        response.body()?.let { mListener.onFinishSuccess(it) }
    }

    override fun stopLoadingCountries() {
        if(mCountriesCall != null){
            mCountriesCall.cancel()
        }
    }
}