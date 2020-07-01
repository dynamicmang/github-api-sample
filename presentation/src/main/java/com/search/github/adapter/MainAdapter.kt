package com.search.github.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.search.github.R
import com.search.github.databinding.ListItemMainBinding

class MainAdapter(private val items: List<String>, private val callbacks: Callbacks?) : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    interface Callbacks {
        fun onItemStatus(type: String, view: View, entity: String)
    }

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val binding: ListItemMainBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.list_item_main, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.binding.data = items[position]
        holder.binding.executePendingBindings()
    }

    inner class MainViewHolder(val binding: ListItemMainBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener { callbacks?.onItemStatus("CLICK",it, items[adapterPosition]) }
            binding.remove.setOnClickListener { callbacks?.onItemStatus("REMOVE", it, items[adapterPosition]) }
        }
    }

}