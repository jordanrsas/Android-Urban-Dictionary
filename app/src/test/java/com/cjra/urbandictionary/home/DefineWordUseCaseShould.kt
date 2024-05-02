package com.cjra.urbandictionary.home

import com.cjra.urbandictionary.application.presentation.usecases.DefineWord
import com.cjra.urbandictionary.home.common.BaseUnitTestHome
import com.cjra.urbandictionary.home.common.definitionsPlain
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.koin.test.inject
import kotlin.test.assertEquals

class DefineWordUseCaseShould : BaseUnitTestHome() {
    private val sut by inject<DefineWord>()

    @Test
    fun emitDefinitionsData() = runTest {
        happyPath()
        val actual = sut("word").single()
        assertEquals(Result.success(definitionsPlain), actual)
    }

    @Test
    fun notEmitInCaseOfError() = runTest {
        errorCase()

        val actual = sut("word").count()
        assertEquals(1, actual)
    }
}