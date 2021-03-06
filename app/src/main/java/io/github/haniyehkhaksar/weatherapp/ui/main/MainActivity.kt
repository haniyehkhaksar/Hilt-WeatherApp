package io.github.haniyehkhaksar.weatherapp.ui.main

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.Menu
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import dagger.hilt.android.AndroidEntryPoint
import io.github.haniyehkhaksar.weatherapp.R
import io.github.haniyehkhaksar.weatherapp.databinding.ActivityMainBinding
import io.github.haniyehkhaksar.weatherapp.ui.SharedViewModel


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val sharedViewModel: SharedViewModel by viewModels()
    private val viewModel: MainViewModel by viewModels()

    private val cityObserver = Observer<String> { city ->
        if (city.isNullOrEmpty() || city.isNullOrBlank()) {
            viewModel.setContentVisibility(false)
        } else {
            viewModel.setContentVisibility(true)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val databinding : ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        databinding.sharedViewModel = sharedViewModel
        databinding.viewModel = viewModel
        databinding.lifecycleOwner = this
        databinding.executePendingBindings()

        setSupportActionBar(databinding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayShowHomeEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        supportActionBar?.setDisplayUseLogoEnabled(false)
        supportActionBar?.setDisplayShowCustomEnabled(false)

        sharedViewModel.city.observe(this, cityObserver)

    }

    override fun onDestroy() {
        super.onDestroy()
        sharedViewModel.city.removeObserver(cityObserver)
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)

        // Associate searchable configuration with the SearchView
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        (menu.findItem(R.id.search).actionView as SearchView).apply {
            maxWidth = Int.MAX_VALUE
            setSearchableInfo(searchManager.getSearchableInfo(componentName))
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextChange(newText: String): Boolean {
                    if (newText.isEmpty() || newText.isBlank())
                        sharedViewModel.city.value = ""
                    return true
                }

                override fun onQueryTextSubmit(query: String): Boolean {
                    sharedViewModel.city.value = query
                    clearFocus()
                    return true
                }
            })
        }

        return true
    }

}