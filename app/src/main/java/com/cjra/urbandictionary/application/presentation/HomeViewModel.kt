package com.cjra.urbandictionary.application.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cjra.urbandictionary.application.presentation.usecases.DefineWord
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class HomeViewModel(
    private val stateMapper: HomeStateMapper,
    private val defineWord: DefineWord
) : ViewModel() {

    private val initialState = HomeState.Empty
    private val _uiState: MutableStateFlow<HomeState> = MutableStateFlow(initialState)

    val uiState: StateFlow<HomeState> = _uiState.asStateFlow()

    fun handleIntent(intent: HomeIntent) {
        _uiState.value = HomeState.Loading
        viewModelScope.launch(Dispatchers.Main) {
            when (intent) {
                is HomeIntent.DefineWord -> defineWord(intent)
            }
        }
    }

    private suspend fun defineWord(intent: HomeIntent.DefineWord) {
        try {
            defineWord(intent.word).map(stateMapper).collect {
                _uiState.value = it
            }
        } catch (e: Exception) {
            Log.e("Home View Model", e.toString())
        }
    }
}