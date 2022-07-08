package com.kev.mvvigithub.model


data class RepositoryList(val items: ArrayList<RepositoryData>)
data class RepositoryData(val name: String, val description: String, val html_url : String, val owner: Owner)
data class Owner(val avatar_url: String)
