package com.cjra.urbandictionary.application.presentation

sealed class HomeIntent {
    data class DefineWord(val word: String) : HomeIntent()
}