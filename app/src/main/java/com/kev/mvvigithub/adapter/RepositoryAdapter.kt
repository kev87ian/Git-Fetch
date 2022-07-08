package com.kev.mvvigithub.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kev.mvvigithub.R
import com.kev.mvvigithub.model.RepositoryData
import kotlinx.android.synthetic.main.recyclerview_row.view.*

class RepositoryAdapter(val context: Context) :
    RecyclerView.Adapter<RepositoryAdapter.RepositoryViewHolder>() {

    private var items = ArrayList<RepositoryData>()

    fun setListData(data: ArrayList<RepositoryData>) {
        this.items = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_row, parent, false)
        return RepositoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {

        val item = items[position]
        holder.titleTv.text = item.name
        holder.descriptionTV.text = item.description
        holder.url.text = item.html_url
        Glide.with(context).load(item.owner.avatar_url).into(holder.imageView)
    }

    override fun getItemCount(): Int {

        return items.size
    }


    class RepositoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleTv: TextView = view.tvTitle
        val descriptionTV: TextView = view.tvDescription
        val imageView: ImageView = view.imageview
        val url: TextView = view.urlTv
    }


}