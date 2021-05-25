package com.burkclik.movieapp.ui.movie.list.popular

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.burkclik.movieapp.R

class PopularMovieDecorator : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val rightSpacing =
            view.context.resources.getDimensionPixelOffset(R.dimen.item_popular_right_spacing)
        val bottomSpacing =
            view.context.resources.getDimensionPixelOffset(R.dimen.item_popular_bottom_spacing)

        outRect.right = rightSpacing
        outRect.bottom = bottomSpacing
    }
}