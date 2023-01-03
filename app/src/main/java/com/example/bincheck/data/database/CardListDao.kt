package com.example.bincheck.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CardListDao {

    @Query("SELECT * FROM card_list")
    fun cardList(): LiveData<List<CardListEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCardListEntity(cardListEntity: CardListEntity): Long
}