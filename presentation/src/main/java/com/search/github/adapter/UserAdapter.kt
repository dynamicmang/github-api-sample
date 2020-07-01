package com.search.github.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.search.domain.entity.UserDetailEntity
import com.search.github.R
import com.search.github.databinding.ListItemUserBinding

class UserAdapter(private val items: ArrayList<UserDetailEntity>) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    fun addItemList(items: List<UserDetailEntity>) {
        val start = this.items.size
        this.items.addAll(items)
        val end = this.items.size
        notifyItemRangeChanged(start, end)
    }

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ListItemUserBinding = DataBindingUtil.inflate(inflater, R.layout.list_item_user, parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.binding.entity = items[position]
        holder.binding.executePendingBindings()
    }

    inner class UserViewHolder(val binding: ListItemUserBinding) : RecyclerView.ViewHolder(binding.root)

}