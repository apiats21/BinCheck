package com.example.bincheck.data.repository.bindList

import androidx.lifecycle.LiveData
import com.example.bincheck.data.database.CardDatabase
import com.example.bincheck.data.database.CardListEntity
import javax.inject.Inject

class BinListLocalDataSource @Inject constructor(private val database: CardDatabase) {
    val binList: LiveData<List<CardListEntity>> = database.cardListDao().cardList()
}