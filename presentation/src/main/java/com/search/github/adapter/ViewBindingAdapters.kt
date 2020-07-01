package com.search.github.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.search.github.R
import com.search.github.util.EndlessRecyclerOnScrollListener
import com.search.github.viewmodel.SearchViewModel

object ViewBindingAdapters {

    @JvmStatic
    @BindingAdapter("mainList")
    fun setEmptyKeyword(textView: TextView, items: LiveData<List<String>>?) {
        val flag = if (items == null || items.value.isNullOrEmpty()) {
            View.VISIBLE
        } else {
            View.GONE
        }
        textView.visibility = flag
    }

    @JvmStatic
    @BindingAdapter("mainList", "callbacks", requireAll = false)
    fun setMainAdapter(recyclerView: RecyclerView, items: LiveData<List<String>>?, mainCallbacks: MainAdapter.Callbacks) {
        if (items == null || items.value.isNullOrEmpty()) recyclerView.visibility = View.GONE
        else recyclerView.visibility = View.VISIBLE
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = MainAdapter(items?.value ?: emptyList(), mainCallbacks)
    }

    @JvmStatic
    @BindingAdapter("repositoryAdapter")
    fun setRepositoryAdapter(recyclerView: RecyclerView, searchViewModel: SearchViewModel) {
        recyclerView.setHasFixedSize(true)
        recyclerView.addOnScrollListener(object : EndlessRecyclerOnScrollListener(recyclerView.layoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                searchViewModel.getRepository(page)
            }
        })
        recyclerView.adapter = RepositoryAdapter(arrayListOf())
    }

    @JvmStatic
    @BindingAdapter("userAdapter")
    fun setUserAdapter(recyclerView: RecyclerView, searchViewModel: SearchViewModel) {
        recyclerView.setHasFixedSize(true)
        recyclerView.addOnScrollListener(object : EndlessRecyclerOnScrollListener(recyclerView.layoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                searchViewModel.getUser(page)
            }
        })
        recyclerView.adapter = UserAdapter(arrayListOf())
    }

    @JvmStatic
    @BindingAdapter("imageLoad")
    fun imageLoad(imageView: ImageView, url: String) {
        Glide
            .with(imageView)
            .load(url)
            .placeholder(R.drawable.baseline_face_black_18dp)
            .centerCrop()
            .into(imageView)
    }

}
