package com.achmadabrar.movieapp_mandiri.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.achmadabrar.movieapp_mandiri.R
import com.achmadabrar.movieapp_mandiri.data.model.Result
import com.achmadabrar.movieapp_mandiri.presentation.viewholder.ListMovieViewHolder

class ListMoviePagedListAdapter(
    val listener: ListMovieViewHolder.Listener?
) : PagedListAdapter<Result, RecyclerView.ViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Result>() {
            override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
                return oldItem.id == newItem.id
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ListMovieViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_movie_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ListMovieViewHolder -> {
                getItem(position).let { holder.bind(it, listener) }
            }
        }
    }
}