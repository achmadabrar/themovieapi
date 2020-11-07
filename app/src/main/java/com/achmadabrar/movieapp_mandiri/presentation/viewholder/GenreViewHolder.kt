package com.achmadabrar.movieapp_mandiri.presentation.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.achmadabrar.movieapp_mandiri.data.model.Genre
import kotlinx.android.synthetic.main.item_genre_layout.view.*

class GenreViewHolder(itemView:View): RecyclerView.ViewHolder(itemView) {

    fun bind(genres: Genre?, listener: OnClickListener?) {
        with(itemView) {
            tv_genre_title.text = genres?.name
            container.setOnClickListener {
                listener?.onClickGenre(genres)
            }
        }
    }

    interface OnClickListener {
        fun onClickGenre(genres: Genre?)
    }
}