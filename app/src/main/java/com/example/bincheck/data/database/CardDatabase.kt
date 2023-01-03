package com.example.bincheck.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CardListEntity::class], version = 1, exportSchema = false)
abstract class CardDatabase: RoomDatabase() {
    abstract fun cardListDao(): CardListDao

    companion object {
        fun buildDatabase(context: Context): CardDatabase {
            return Room.databaseBuilder(context, CardDatabase::class.java, "Cards").build()
        }
    }
}