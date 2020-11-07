package com.achmadabrar.movieapp_mandiri.presentation.itemdecoration

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.achmadabrar.movieapp_mandiri.external.dpToPx

class ItemDecoration : RecyclerView.ItemDecoration(){

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        val position = parent.getChildAdapterPosition(view)
        val totalSpanCount = getTotalSpanCount(parent)
        val itemCount = state.itemCount

        if (position != RecyclerView.NO_POSITION) {
            outRect.top =
                if (isInTheFirstRow(position + 2, totalSpanCount)) 0 else 6.dpToPx()
            outRect.bottom = if (isInTheLastRow(
                    position + 2,
                    totalSpanCount,
                    itemCount
                )
            ) 12.dpToPx() else 6.dpToPx()
            outRect.left =
                if (isFirstInRow(position + 2, totalSpanCount)) 12.dpToPx() else 6.dpToPx()
            outRect.right =
                if (isFirstInRow(position + 2, totalSpanCount)) 6.dpToPx() else 12.dpToPx()
        }
    }

    //first row
    private fun isInTheFirstRow(position: Int, spanCount: Int): Boolean {
        return position < spanCount
    }

    //last row
    private fun isInTheLastRow(position: Int, spanCount: Int, itemCount: Int): Boolean {
        return itemCount > 0 && position == itemCount - 1
    }

    //first item in a row
    private fun isFirstInRow(position: Int, spanCount: Int): Boolean {
        return position % spanCount == 0
    }

    private fun getTotalSpanCount(parent: RecyclerView): Int {
        val layoutManager = parent.layoutManager
        return (layoutManager as? GridLayoutManager)?.spanCount ?: 1
    }
}