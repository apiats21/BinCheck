package com.example.bincheck.data.repository.bindList

import javax.inject.Inject

class BinListRepository @Inject constructor(
    binListLocalDataSource: BinListLocalDataSource
) {
    val binList = binListLocalDataSource.binList
}