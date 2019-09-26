package com.noob.apps.mvpretrofit.presenters

import android.content.Context
import com.noob.apps.mvpretrofit.interfaces.MainContract
import com.noob.apps.mvpretrofit.models.CountriesModel
import com.noob.apps.mvpretrofit.models.Country
import com.noob.apps.mvpretrofit.utils.NetworkHelper

class MainActivityPresenter : MainContract.Presenter, MainContract.Model.OnFinishListener {
    private var mModel: CountriesModel
    private var mView: MainContract.View?

    constructor(view: MainContract.View) {
        mView = view
        mModel = CountriesModel()
    }

    override fun fetchCountriesFromNetwork(context: Context) {
        if (NetworkHelper.isNetworkAvailable(context)) {
            mView!!.showProgress()
            mModel.loadCountries(this)
        } else {
            mView!!.showNetworkError()
        }
    }

    override fun onDestroy() {
        mView = null;
        mModel.stopLoadingCountries()
    }

    override fun onFinishSuccess(countryList: List<Country>) {
        mView?.let {
            mView!!.hideProgress()
            mView!!.updateGui(countryList)
        }
    }

    override fun onFinishFailure(throwable: Throwable) {
        mView?.let {
            mView!!.hideProgress()
            mView!!.showError(throwable)
        }
    }
}