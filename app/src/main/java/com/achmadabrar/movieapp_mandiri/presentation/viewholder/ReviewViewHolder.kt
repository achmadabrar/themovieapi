package com.achmadabrar.movieapp_mandiri.presentation.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.achmadabrar.movieapp_mandiri.data.model.Result
import com.achmadabrar.movieapp_mandiri.data.model.ResultReview
import kotlinx.android.synthetic.main.item_review_user.view.*

class ReviewViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    fun bindItem(review: ResultReview?, listener: Listener) {
        with(itemView) {
            tv_author.text = review?.author
            tv_review.text = review?.content
            tv_url.text = review?.url

            itemView.setOnClickListener {
                listener.onClick(review?.url)
            }
        }
    }

    interface Listener {
        fun onClick(url: String?)
    }
}