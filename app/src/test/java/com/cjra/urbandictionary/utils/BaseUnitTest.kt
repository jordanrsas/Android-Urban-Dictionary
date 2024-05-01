package com.cjra.urbandictionary.utils

import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.koin.test.KoinTest
import org.koin.test.mock.MockProviderRule
import org.mockito.Mockito

open class BaseUnitTest : KoinTest {
    @get:Rule
    val mockProvider = MockProviderRule.create { clazz ->
        Mockito.mock(clazz.java)
    }


    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesTestRule = MainCoroutineScopeRule()
}