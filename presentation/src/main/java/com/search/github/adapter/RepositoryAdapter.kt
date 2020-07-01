package com.search.github.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.search.domain.entity.RepositoryEntity
import com.search.github.R
import com.search.github.databinding.ListItemRepositoryBinding

class RepositoryAdapter(private val items: ArrayList<RepositoryEntity>) : RecyclerView.Adapter<RepositoryAdapter.RepositoryViewHolder>() {

    fun addItemList(items: List<RepositoryEntity>) {
        val start = this.items.size
        this.items.addAll(items)
        val end = this.items.size
        notifyItemRangeChanged(start, end)
    }

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ListItemRepositoryBinding = DataBindingUtil.inflate(inflater, R.layout.list_item_repository, parent, false)
        return RepositoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        holder.binding.entity = items[position]
        holder.binding.executePendingBindings()
    }

    inner class RepositoryViewHolder(val binding: ListItemRepositoryBinding) : RecyclerView.ViewHolder(binding.root)

}