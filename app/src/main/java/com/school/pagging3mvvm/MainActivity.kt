package com.school.pagging3mvvm

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.school.pagging3mvvm.pagging.LoaderAdapter
import com.school.pagging3mvvm.pagging.PostLoadStateAdapter
import com.school.pagging3mvvm.pagging.PostPagingAdapter
import com.school.pagging3mvvm.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var postModelView: MainViewModel
    lateinit var recyclerView: RecyclerView
    lateinit var postAdapter: PostPagingAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        recyclerView = findViewById(R.id.quoteList)
        postModelView = ViewModelProvider(this).get(MainViewModel::class.java)
        postAdapter = PostPagingAdapter()


        recyclerView.adapter = postAdapter.withLoadStateHeaderAndFooter(
            header = LoaderAdapter(),
            footer = LoaderAdapter()
        )

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = postAdapter

        postModelView.posts.observe(this, Observer {
            postAdapter.submitData(lifecycle, it)

        })

    }
}