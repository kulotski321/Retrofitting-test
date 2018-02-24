package com.example.cf.retrofitting

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(){

    var textView: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.pagination_list)


        val builder = Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
        val retrofit: Retrofit = builder.build()

        val client: GitHubClient = retrofit.create(GitHubClient::class.java)
        val call : Call<List<GitHubRepo>> = client.reposForUser("fs-opensource")

        call.enqueue(object :Callback<List<GitHubRepo>>{
            override fun onResponse(call: Call<List<GitHubRepo>>, response: Response<List<GitHubRepo>>){
                val repos: List<GitHubRepo>? = response.body()

                pagination_list.text = repos.toString()
            }
            override fun onFailure(call: Call<List<GitHubRepo>>,t: Throwable){

            }
        })



    }
}
