package com.example.testtask.decorators

import android.graphics.Rect
import android.view.View

import androidx.recyclerview.widget.RecyclerView

class MarginItemDecoration(private val spaceTop: Int, private val spaceSide: Int, private val spaceBottom: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)

        outRect.top = spaceTop
        outRect.left = spaceSide
        outRect.right = spaceSide
        outRect.bottom = spaceBottom
    }
}