package com.example.acronyms.api

import com.example.acronyms.model.Acronyms
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiInterface {
    @GET("dictionary.py")
    fun search(
        @Query("sf")
        input: String
    ): Call<List<Acronyms>>

    companion object {
        var BASE_URL = "http://www.nactem.ac.uk/software/acromine/"

        fun create(): ApiInterface {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(ApiInterface::class.java)

        }
    }
}
