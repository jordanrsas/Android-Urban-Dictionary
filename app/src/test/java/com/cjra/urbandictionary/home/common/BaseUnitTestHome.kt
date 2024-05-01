package com.cjra.urbandictionary.home.common

import com.cjra.urbandictionary.utils.BaseUnitTest
import org.junit.After
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin

open class BaseUnitTestHome : BaseUnitTest() {
    protected fun happyPath() {
        startKoin {
            modules(homeModuleHappy)
        }
    }

    protected fun emptyResultPath() {
        startKoin {
            modules(homeModuleEmpty)
        }
    }

    protected fun errorCase() {
        startKoin {
            modules(homeModuleError)
        }
    }

    @After
    fun tearDown() {
        stopKoin()
    }
}