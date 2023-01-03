package com.example.bincheck.provider

import com.example.bincheck.domain.model.Card
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {

    @GET("{bin}")
    suspend fun bin(
        @Path("bin") bin: String
    ): Response<Card>

}