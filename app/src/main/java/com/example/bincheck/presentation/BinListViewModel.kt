package com.example.bincheck.presentation

import androidx.lifecycle.ViewModel
import com.example.bincheck.data.repository.bindList.BinListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BinListViewModel @Inject constructor(private val repository: BinListRepository): ViewModel() {
    val binList = repository.binList
}