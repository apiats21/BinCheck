package com.example.bincheck.domain.model

data class Card(
    val number: Number?,
    val scheme: String?,
    val type: String?,
    val brand: String?,
    val prepaid: Boolean?,
    val country: Country?,
    val bank: Bank?
)

data class Number(
    val length: Int?,
    val luhn: Boolean?
)

data class Country(
    val numeric: String?,
    val alpha2: String?,
    val name: String?,
    val emoji: String?,
    val currency: String?,
    val latitude: Double?,
    val longitude: Double?
)

data class Bank(
    val name: String?,
    val url: String?,
    val phone: String?,
    val city: String?
)

