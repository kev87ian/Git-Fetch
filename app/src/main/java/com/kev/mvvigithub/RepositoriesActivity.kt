package com.kev.mvvigithub

import android.app.ProgressDialog
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kev.mvvigithub.adapter.RepositoryAdapter
import com.kev.mvvigithub.model.RepositoryList
import com.kev.mvvigithub.viewmodel.RepositoryActivityViewModel

class RepositoriesActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var repositoryAdapter: RepositoryAdapter
    lateinit var mDialog: ProgressDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repositories)

        initRecyclerView()
        livedataListener()
    }


    private fun livedataListener() {

        val viewModel = ViewModelProviders.of(this).get(RepositoryActivityViewModel::class.java)
        viewModel.getRepositoryLiveDataObserver().observe(this, Observer<RepositoryList> {
            if (it != null) {
                repositoryAdapter.setListData(it.items)
                repositoryAdapter.notifyDataSetChanged()
            } else {
                Toast.makeText(baseContext, "Error fetching data", Toast.LENGTH_LONG).show()
            }

        })
        viewModel.makeAPICall()
    }

    private fun initRecyclerView() {
        recyclerView = findViewById(R.id.recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(this)
        repositoryAdapter = RepositoryAdapter(this)
        recyclerView.adapter = repositoryAdapter
        mDialog = ProgressDialog(this)
    }


}




