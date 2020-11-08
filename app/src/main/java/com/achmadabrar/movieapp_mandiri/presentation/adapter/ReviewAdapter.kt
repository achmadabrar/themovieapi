package com.achmadabrar.movieapp_mandiri.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.achmadabrar.movieapp_mandiri.R
import com.achmadabrar.movieapp_mandiri.data.model.ResponseReview
import com.achmadabrar.movieapp_mandiri.presentation.viewholder.ReviewViewHolder

class ReviewAdapter(
    val resultReview: ResponseReview,
    val listener: ReviewViewHolder.Listener
    ) : RecyclerView.Adapter<ReviewViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        return ReviewViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_review_user, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        resultReview.results[position].let {
            holder.bindItem(it, listener)
        }
    }

    override fun getItemCount(): Int {
        return resultReview.results.size
    }
}