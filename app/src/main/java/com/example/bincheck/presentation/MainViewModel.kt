package com.example.bincheck.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bincheck.provider.Result
import com.example.bincheck.data.database.CardListEntity
import com.example.bincheck.data.repository.main.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: MainRepository): ViewModel() {
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _toastError = MutableLiveData<String>()
    val toastError: LiveData<String> = _toastError

    private val _cardStock = MutableLiveData<CardListEntity?>()
    val cardStock: LiveData<CardListEntity?> = _cardStock

    fun loadCardFromApi(bin: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.postValue(true)
            when(val result = repository.loadCardByBin(bin)) {
                is Result.Success -> { _cardStock.postValue(result.data) }
                is Result.Error -> { _toastError.postValue(result.message) }
            }
            _isLoading.postValue(false)
        }
    }
}