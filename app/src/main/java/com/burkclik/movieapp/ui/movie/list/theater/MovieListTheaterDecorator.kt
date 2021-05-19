package com.burkclik.movieapp.ui.movie.list.theater

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.burkclik.movieapp.R

class MovieListTheaterDecorator : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val spacing = view.context.resources.getDimensionPixelOffset(R.dimen.item_theater_spacing)
        outRect.right = spacing
    }
}