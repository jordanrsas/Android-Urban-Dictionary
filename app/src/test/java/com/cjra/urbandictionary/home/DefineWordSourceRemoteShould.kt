package com.cjra.urbandictionary.home

import com.cjra.urbandictionary.application.presentation.usecases.DefineWordSource
import com.cjra.urbandictionary.home.common.BaseUnitTestHome
import com.cjra.urbandictionary.home.common.definitionsPlain
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.koin.test.inject
import kotlin.test.assertEquals
import kotlin.test.assertNull

class DefineWordSourceRemoteShould : BaseUnitTestHome() {
    private val sut by inject<DefineWordSource>()

    @Test
    fun defineWordData() = runTest {
        happyPath()

        val actual = sut.defineWord("word").single()
        assertEquals(definitionsPlain, actual)
    }

    @Test
    fun doesNotEmitInCaseOfError() = runTest {
        errorCase()
        val actual = sut.defineWord("word").single().count()
        assertEquals(0, actual)
    }
}