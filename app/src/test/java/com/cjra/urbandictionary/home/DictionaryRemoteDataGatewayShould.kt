package com.cjra.urbandictionary.home

import com.cjra.urbandictionary.application.data.remote.DictionaryDataSourceRemote
import com.cjra.urbandictionary.home.common.BaseUnitTestHome
import com.cjra.urbandictionary.home.common.definitionsPlain
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.test.runTest
import org.koin.test.inject
import kotlin.test.Test
import kotlin.test.assertEquals

class DictionaryRemoteDataGatewayShould : BaseUnitTestHome() {

    private val sut by inject<DictionaryDataSourceRemote>()

    @Test
    fun mapAndEmitDefinitionsPlainFromService() = runTest {
        happyPath()
        val actual = sut.defineWord("word").single()

        assertEquals(definitionsPlain, actual)
    }

    @Test
    fun notCrashAndNotEmitErrors() = runTest {
        errorCase()
        val actual = sut.defineWord("word").count()
        assertEquals(1, actual)
    }

    @Test
    fun mapAndEmitEmptyDefinitions() = runTest {
        emptyResultPath()
        val actual = sut.defineWord("word").single().count()
        assertEquals(0, actual)
    }
}