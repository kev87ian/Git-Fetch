package com.kev.mvvigithub

import android.app.ProgressDialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
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
    lateinit var searchButton: Button
    lateinit var editText: EditText
    lateinit var mDialog: ProgressDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repositories)

        initViews()
        livedataListener()
    }


    private fun livedataListener() {


        val viewModel = ViewModelProviders.of(this).get(RepositoryActivityViewModel::class.java)
        viewModel.getRepositoryLiveDataObserver().observe(this, Observer<RepositoryList> {
            if (it != null) {
                repositoryAdapter.setListData(it.items)
                repositoryAdapter.notifyDataSetChanged()
                mDialog.hide()
            } else {
                Toast.makeText(baseContext, "Error fetching data", Toast.LENGTH_LONG).show()
                mDialog.hide()
            }

        })
        searchButton.setOnClickListener {
            viewModel.makeAPICall(editText.text.toString().trim())
            mDialog = ProgressDialog(this)
            mDialog.setMessage("Please wait...")
            mDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER)
            mDialog.isIndeterminate = true
            mDialog.setCancelable(false)
            mDialog.show()
        }

    }

    private fun initViews() {
        searchButton = findViewById(R.id.search_Button_it)
        editText = findViewById(R.id.search_edi_text)

        recyclerView = findViewById(R.id.recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(this)

        repositoryAdapter = RepositoryAdapter(this)
        recyclerView.adapter = repositoryAdapter



    }


}




