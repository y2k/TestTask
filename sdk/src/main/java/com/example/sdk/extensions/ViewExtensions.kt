package com.example.sdk.extensions

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

fun View.isVisible() = this.visibility == View.VISIBLE

fun View.visible() { this.visibility = View.VISIBLE }

fun View.invisible() { this.visibility = View.INVISIBLE }

fun View.gone() { this.visibility = View.GONE }

//Fragment
fun ViewGroup.inflate(layoutId: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)
}

//RV
fun verticalManager(context: Context?, reverseLayout: Boolean = false): LinearLayoutManager {
    return LinearLayoutManager(context, RecyclerView.VERTICAL, reverseLayout)
}

fun horizontalManager(context: Context?, reverseLayout: Boolean = false): LinearLayoutManager {
    return LinearLayoutManager(context, RecyclerView.HORIZONTAL, reverseLayout)
}

fun gridManager(context: Context?, spanCount: Int = 2, orientation: Int = RecyclerView.VERTICAL, reverseLayout: Boolean = false): GridLayoutManager {
    return GridLayoutManager(context, spanCount, orientation, reverseLayout)
}