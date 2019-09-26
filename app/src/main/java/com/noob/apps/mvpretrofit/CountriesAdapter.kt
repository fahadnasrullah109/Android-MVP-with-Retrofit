package com.noob.apps.mvpretrofit

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ahmadrosid.svgloader.SvgLoader
import com.noob.apps.mvpretrofit.models.Country

class CountriesAdapter(private val mContext: Context, private var list: List<Country>) :
    RecyclerView.Adapter<CountriesAdapter.ViewHolder>() {

    public fun updateList(list: List<Country>) {
        this.list = list
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(mContext).inflate(
                R.layout.item_country,
                parent,
                false
            )
        )
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.titleTv.text = this.list.get(position).name
        holder.descriptionTv.text = this.list.get(position).capital
        SvgLoader.pluck().with(mContext as Activity?).load(this.list.get(position).flag, holder.thumbIv)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleTv : TextView = view.findViewById(R.id.titleTv)
        val descriptionTv : TextView = view.findViewById(R.id.descriptionTv)
        val thumbIv : ImageView = view.findViewById(R.id.thumbIv)
    }
}