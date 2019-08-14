package com.example.sdk.utils

import android.content.Context
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

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
