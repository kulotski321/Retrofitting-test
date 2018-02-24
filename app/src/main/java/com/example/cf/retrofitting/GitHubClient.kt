package com.example.cf.retrofitting


import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by CF on 2/24/2018.
 */
interface GitHubClient {

    @GET("/users/{user}/repos")
    fun reposForUser(@Path("user") user: String) : Call<List<GitHubRepo>>
}