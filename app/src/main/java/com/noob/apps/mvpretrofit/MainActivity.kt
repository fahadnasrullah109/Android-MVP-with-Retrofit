package com.noob.apps.mvpretrofit

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ahmadrosid.svgloader.SvgLoader
import com.noob.apps.mvpretrofit.interfaces.MainContract
import com.noob.apps.mvpretrofit.models.Country
import com.noob.apps.mvpretrofit.presenters.MainActivityPresenter

class MainActivity : AppCompatActivity(), MainContract.View {
    private lateinit var mProgressBar: ProgressBar
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mPresenter: MainActivityPresenter
    private lateinit var mAdapter: CountriesAdapter
    private var mListItems: List<Country> = mutableListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mProgressBar = findViewById(R.id.progressBar)
        mRecyclerView = findViewById(R.id.recyclerView)

        mAdapter = CountriesAdapter(this@MainActivity, mListItems)
        mPresenter = MainActivityPresenter(this)
        mPresenter.fetchCountriesFromNetwork(this@MainActivity)
        setUpGui()
    }

    private fun setUpGui() {
        mRecyclerView.layoutManager =
            LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
        mRecyclerView.adapter = mAdapter

    }

    override fun showProgress() {
        mProgressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        mProgressBar.visibility = View.GONE
    }

    override fun updateGui(countryList: List<Country>) {
        this.mListItems = countryList
        mAdapter.updateList(this.mListItems)
    }

    override fun showNetworkError() {
        Toast.makeText(this, getString(R.string.network_error), Toast.LENGTH_LONG).show()
    }

    override fun showError(throwable: Throwable) {
        Toast.makeText(this, getString(R.string.generic_error), Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.onDestroy()
        SvgLoader.pluck().close();
    }

}
