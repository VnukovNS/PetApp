package com.example.petapp.core.presentation.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class GenericViewHolder<T : Any>(view: View) : RecyclerView.ViewHolder(view) {

    abstract fun bind(item: T)
}