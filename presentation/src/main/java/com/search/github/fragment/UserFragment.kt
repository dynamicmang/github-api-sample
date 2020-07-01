package com.search.github.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.search.github.adapter.UserAdapter
import com.search.github.databinding.FragmentUserBinding
import com.search.github.viewmodel.SearchViewModel
import kotlinx.android.synthetic.main.fragment_user.*

class UserFragment : Fragment() {

    companion object {
        const val TITLE = "User"
        fun newInstance() = UserFragment()
    }

    private val searchViewModel: SearchViewModel by lazy {
        ViewModelProvider(activity!!).get(SearchViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentUserBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = searchViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchViewModel.userData.observe(activity!!, Observer {
            val adapter = recycler_view.adapter as? UserAdapter
            adapter?.addItemList(it)
        })
    }

}