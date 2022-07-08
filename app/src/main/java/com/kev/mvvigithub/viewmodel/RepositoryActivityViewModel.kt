package com.kev.mvvigithub.viewmodel

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kev.mvvigithub.model.RepositoryData
import com.kev.mvvigithub.model.RepositoryList
import com.kev.mvvigithub.network.RetrofitInstance
import com.kev.mvvigithub.network.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepositoryActivityViewModel : ViewModel() {
    lateinit var repositoryListData: MutableLiveData<RepositoryList>

    init {
        repositoryListData  = MutableLiveData()
    }

    fun getRepositoryLiveDataObserver() : MutableLiveData<RepositoryList>{
        return repositoryListData
    }

    fun makeAPICall(input : String){

        val retrofitInstance = RetrofitInstance.getRetrofitInstance().create(RetrofitService::class.java)
        val call = retrofitInstance.getDataFromAPI(input)
        call.enqueue(object : Callback<RepositoryList?> {
            override fun onResponse(call: Call<RepositoryList?>, response: Response<RepositoryList?>) {
                if (response.isSuccessful){
                    repositoryListData.postValue(response.body())
                }

                else{
                    repositoryListData.postValue(null)
                }
            }

            override fun onFailure(call: Call<RepositoryList?>, t: Throwable) {

            repositoryListData.postValue(null)
            }
        })
    }
}