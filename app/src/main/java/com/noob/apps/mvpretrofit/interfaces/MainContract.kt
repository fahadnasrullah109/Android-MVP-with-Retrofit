package com.noob.apps.mvpretrofit.interfaces

import android.content.Context
import com.noob.apps.mvpretrofit.models.Country

interface MainContract {

    interface Model {
        interface OnFinishListener {
            fun onFinishSuccess(countryList: List<Country>)
            fun onFinishFailure(throwable: Throwable)
        }

        fun loadCountries(listener: OnFinishListener)
        fun stopLoadingCountries()
    }

    interface View {
        fun showProgress();
        fun hideProgress();
        fun updateGui(countryList: List<Country>)
        fun showNetworkError()
        fun showError(throwable: Throwable)
    }

    interface Presenter {
        fun onDestroy()
        fun fetchCountriesFromNetwork(context: Context)
    }
}