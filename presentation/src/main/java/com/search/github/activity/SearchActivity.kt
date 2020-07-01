package com.search.github.activity

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayoutMediator
import com.search.github.R
import com.search.github.adapter.PagerAdapter
import com.search.github.databinding.ActivitySearchBinding
import com.search.github.fragment.RepositoryFragment
import com.search.github.fragment.UserFragment
import com.search.github.viewmodel.SearchViewModel
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_search.*
import javax.inject.Inject

class SearchActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel: SearchViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(SearchViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setView()
        getQuery()
    }

    private fun setView() {
        DataBindingUtil.setContentView<ActivitySearchBinding>(this, R.layout.activity_search).apply {
            lifecycleOwner = this@SearchActivity
        }
        view_pager.adapter = PagerAdapter(this)
        view_pager.offscreenPageLimit = 2
        TabLayoutMediator(tab_layout, view_pager,
            TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                when (position) {
                    0 -> tab.text = RepositoryFragment.TITLE
                    1 -> tab.text = UserFragment.TITLE
                }
            }).attach()
    }

    private fun getQuery() {
        if (intent == null || intent.getStringExtra(SearchActivity::class.java.simpleName).isNullOrEmpty()) {
            finish()
            return
        }
        val query = intent.getStringExtra(SearchActivity::class.java.simpleName)
        viewModel.query = query
    }

}