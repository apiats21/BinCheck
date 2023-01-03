package com.example.bincheck.data.repository.main

import com.example.bincheck.provider.ApiInterface
import com.example.bincheck.domain.model.Card
import javax.inject.Inject
import com.example.bincheck.provider.Result
import com.example.bincheck.domain.Constants

class MainRemoteDataSource @Inject constructor(private val service: ApiInterface) {

    suspend fun bin(bin: String): Result<Card> {
        try {
            val response = service.bin(bin)
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) return Result.Success(body)
            }
            return Result.Error(Constants.GENERIC_ERROR)
        } catch (e: Exception) {
            return Result.Error(e.message ?: e.toString())
        }
    }
}