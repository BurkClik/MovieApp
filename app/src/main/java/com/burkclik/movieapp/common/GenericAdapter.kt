package com.burkclik.movieapp.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class GenericAdapter<T : BaseModel>(@LayoutRes val layoutId: Int) :
    ListAdapter<T, GenericAdapter.GenericViewHolder<T>>(BaseItemCallback<T>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenericViewHolder<T> {
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context),
            layoutId,
            parent,
            false
        )
        return GenericViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GenericViewHolder<T>, position: Int) {
        holder.bind(getItem(position))
    }

    class GenericViewHolder<T : BaseModel>(private val binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(itemViewModel: T) {
            binding.setVariable(BR.baseModel, itemViewModel)
            binding.executePendingBindings()
        }
    }
}