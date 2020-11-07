package com.achmadabrar.movieapp_mandiri.presentation.viewholder

import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.achmadabrar.movieapp_mandiri.data.model.Result
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.list_movie_item.view.*

class ListMovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(result: Result?, listener: Listener?) {
        with(itemView) {
            Glide.with(this)
                .load("https://image.tmdb.org/t/p/original/" + result?.posterPath)
                .into(imageViewMovie)

            textViewTitle.text = result?.title
            textViewDesc.text = result?.overview
            vote.text = result?.vote.toString()

            itemView.setOnClickListener {
                listener?.onClickMovie(result)
            }
        }
    }

    interface Listener {
        fun onClickMovie(result: Result?)
    }
}