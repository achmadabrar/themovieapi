package com.achmadabrar.movieapp_mandiri.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.achmadabrar.movieapp_mandiri.R
import com.achmadabrar.movieapp_mandiri.data.model.Result
import com.achmadabrar.movieapp_mandiri.presentation.viewholder.ListMovieViewHolder

class ListMovieAdapter(
    val listMovie: List<Result>?,
    val listener: ListMovieViewHolder.Listener
): RecyclerView.Adapter<ListMovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListMovieViewHolder {
        return ListMovieViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_movie_item, parent, false))
    }

    override fun onBindViewHolder(holder: ListMovieViewHolder, position: Int) {
        listMovie?.get(position).let {
            holder.bind(it, listener)
        }
    }

    override fun getItemCount(): Int {
        return listMovie?.size!!
    }
}