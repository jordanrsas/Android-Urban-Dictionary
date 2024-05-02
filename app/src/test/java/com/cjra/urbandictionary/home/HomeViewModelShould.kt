package com.cjra.urbandictionary.home

import com.cjra.urbandictionary.application.presentation.HomeIntent
import com.cjra.urbandictionary.application.presentation.HomeState
import com.cjra.urbandictionary.application.presentation.HomeViewModel
import com.cjra.urbandictionary.home.common.BaseUnitTestHome
import com.cjra.urbandictionary.home.common.successfulState
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.koin.test.inject
import kotlin.test.assertEquals

class HomeViewModelShould : BaseUnitTestHome() {

    private val sut by inject<HomeViewModel>()

    @Test
    fun emitsEmptyStateInitially() {
        happyPath()

        val actual = sut.uiState.value

        assertEquals(HomeState.Empty, actual)
    }

    @Test
    fun emitSuccessfulStateAfterDefinitionsIntent() = runTest {
        happyPath()

        sut.handleIntent(HomeIntent.DefineWord("word"))

        val actual = sut.uiState.value

        assertEquals(successfulState, actual)
    }

    @Test
    fun emitEmptyStateWhenEmptyListIsReceived() = runTest {
        emptyResultPath()

        sut.handleIntent(HomeIntent.DefineWord("word"))

        val actual = sut.uiState.value

        assertEquals(HomeState.Empty, actual)
    }

    @Test
    fun notCrashAndNotPropagateError() = runTest {
        errorCase()

        sut.handleIntent(HomeIntent.DefineWord("word"))

        val actual = sut.uiState.value

        assertEquals(HomeState.Empty, actual)
    }
}