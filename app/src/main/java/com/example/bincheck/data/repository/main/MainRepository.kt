package com.example.bincheck.data.repository.main

import com.example.bincheck.data.database.CardListEntity
import com.example.bincheck.domain.Constants
import javax.inject.Inject
import com.example.bincheck.provider.Result

class MainRepository @Inject constructor(
    private val mainRemoteDataSource: MainRemoteDataSource,
    private val mainLocalDataSource: MainLocalDataSource
) {

    suspend fun loadCardByBin(bin: String): Result<CardListEntity> {
        when(val result = mainRemoteDataSource.bin(bin)) {
            is Result.Success -> {
                result.data.let { card ->
                    val cardListEntity = CardListEntity(
                        bin,
                        card.scheme,
                        card.type,
                        card.brand,
                        card.prepaid,
                        card.number?.length,
                        card.number?.luhn,
                        card.country?.name,
                        card.country?.emoji,
                        card.country?.latitude,
                        card.country?.longitude,
                        card.bank?.name,
                        card.bank?.url,
                        card.bank?.phone,
                        card.bank?.city
                    )
                    val insertResult = mainLocalDataSource.insertCardIntoDatabase(cardListEntity)
                    if (insertResult > 0L) { return Result.Success(cardListEntity) }
                }
            }
            else -> return Result.Error(Constants.GENERIC_ERROR)
        }
        return Result.Error(Constants.GENERIC_ERROR)
    }
}