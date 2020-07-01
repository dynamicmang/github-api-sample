package com.search.github.activity

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.search.github.R
import com.search.github.adapter.MainAdapter
import com.search.github.databinding.ActivityMainBinding
import com.search.github.viewmodel.MainViewModel
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity(), MainAdapter.Callbacks, TextView.OnEditorActionListener {

    companion object {
        const val CLICK = "CLICK"
        const val REMOVE = "REMOVE"
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val mainViewModel: MainViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main).apply {
            activity = this@MainActivity
            lifecycleOwner = this@MainActivity
            viewModel = mainViewModel
        }
    }

    private fun moveToSearchActivity(query: String) {
        Intent(this, SearchActivity::class.java).apply {
            putExtra(SearchActivity::class.java.simpleName, query)
            startActivity(this)
        }
    }

    fun clickSearch(query: String) {
        moveToSearchActivity(query)
        mainViewModel.clickSearch(query)
    }

    override fun onEditorAction(p0: TextView?, p1: Int, p2: KeyEvent?): Boolean {
        when (p1) {
            EditorInfo.IME_ACTION_SEARCH -> clickSearch(p0?.text.toString())
        }
        return true
    }

    override fun onItemStatus(type: String, view: View, query: String) {
        when (type) {
            CLICK -> moveToSearchActivity(query)
            REMOVE -> mainViewModel.removeKeyword(query)
        }
    }

}
