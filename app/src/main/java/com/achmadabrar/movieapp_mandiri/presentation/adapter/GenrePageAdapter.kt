package com.achmadabrar.movieapp_mandiri.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.achmadabrar.movieapp_mandiri.R
import com.achmadabrar.movieapp_mandiri.data.model.Genre
import com.achmadabrar.movieapp_mandiri.presentation.viewholder.GenreViewHolder

class GenrePageAdapter(
    val responseGenres: List<Genre>?,
    val listener: GenreViewHolder.OnClickListener
) : RecyclerView.Adapter<GenreViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        return GenreViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_genre_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        responseGenres?.get(position).let {
            holder.bind(it, listener)
        }
    }

    override fun getItemCount(): Int {
        return responseGenres?.size!!
    }
}