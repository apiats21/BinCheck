package com.example.bincheck.data.repository.main

import com.example.bincheck.data.database.CardDatabase
import com.example.bincheck.data.database.CardListEntity
import javax.inject.Inject

class MainLocalDataSource @Inject constructor(private val database: CardDatabase) {

    suspend fun insertCardIntoDatabase(card: CardListEntity): Long {
        return database.cardListDao().insertCardListEntity(card)
    }
}