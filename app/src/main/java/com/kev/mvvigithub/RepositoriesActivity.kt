package com.kev.mvvigithub

import android.app.ProgressDialog
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kev.mvvigithub.adapter.RepositoryAdapter
import com.kev.mvvigithub.model.RepositoryList
import com.kev.mvvigithub.network.RetrofitInstance
import com.kev.mvvigithub.network.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepositoriesActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var repositoryAdapter: RepositoryAdapter
    lateinit var mDialog: ProgressDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repositories)

        initRecyclerView()
        fetchData()
    }

    private fun initRecyclerView() {
        recyclerView = findViewById(R.id.recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(this)
        repositoryAdapter = RepositoryAdapter(this)
        recyclerView.adapter = repositoryAdapter
        mDialog = ProgressDialog(this)
    }


    private fun fetchData() {
        mDialog.setMessage("Working...")
        mDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER)
        mDialog.isIndeterminate = true
        mDialog.setCancelable(false)
        mDialog.show()
        val retrofitInstance = RetrofitInstance.getRetrofitInstance().create(RetrofitService::class.java)
        val call = retrofitInstance.getDataFromAPI("newyork")
        call.enqueue(object : Callback<RepositoryList?> {
            override fun onResponse(call: Call<RepositoryList?>, response: Response<RepositoryList?>) {
                if (response.isSuccessful){
                    repositoryAdapter.setListData(response.body()?.items!!)
                    repositoryAdapter.notifyDataSetChanged()
                    mDialog.hide()

                }
            }

            override fun onFailure(call: Call<RepositoryList?>, t: Throwable) {

                Toast.makeText(baseContext, "Error + ${t.localizedMessage}", Toast.LENGTH_LONG).show()
            }
        })
    }


}




