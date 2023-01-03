package com.example.bincheck.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "card_list")
data class CardListEntity(
    @PrimaryKey val bin: String,
    val scheme: String?,
    val type: String?,
    val brand: String?,
    val prepaid: Boolean?,
    val length: Int?,
    val luhn: Boolean?,
    val countryName: String?,
    val emoji: String?,
    val latitude: Double?,
    val longitude: Double?,
    val bankName: String?,
    val url: String?,
    val phone: String?,
    val city: String?
)