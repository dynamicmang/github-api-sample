package com.search.github.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.search.github.fragment.RepositoryFragment
import com.search.github.fragment.UserFragment

class PagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> RepositoryFragment.newInstance()
            1 -> UserFragment.newInstance()
            else -> UserFragment.newInstance()
        }
    }

}