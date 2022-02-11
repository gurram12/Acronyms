package com.example.acronyms.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.acronyms.R
import com.example.acronyms.databinding.ActivityMainBinding
import com.example.acronyms.viewModel.AcronymsViewModel
import com.example.acronyms.model.LF

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var mainViewModel: AcronymsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        mainViewModel = AcronymsViewModel()
        val recyclerView = findViewById<RecyclerView>(R.id.searchresults)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = CustomAdapter(listOf<LF>())

        binding.mainViewModel = mainViewModel
        mainViewModel.searchResults.observe(
            this, {
                recyclerView.adapter = CustomAdapter(it.lfs)
            }
        )
    }
}