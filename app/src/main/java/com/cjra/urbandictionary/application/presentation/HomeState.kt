package com.cjra.urbandictionary.application.presentation

sealed class HomeState {
    data class Success(val definitions: List<Definition>) : HomeState()
    data object Empty : HomeState()
    data object Loading : HomeState()
    data object Error : HomeState()
}