package com.cjra.urbandictionary.utils

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestWatcher
import org.junit.runner.Description

/**
 * JUnit rule for managing coroutine scopes in unit tests.
 *
 * This rule sets up a test-specific coroutine scope with a [TestCoroutineDispatcher]
 * as the main dispatcher, allowing coroutines launched in tests to run synchronously
 * for predictable behavior and easier testing.
 *
 * Usage:
 * ```
 * @get:Rule
 * val coroutineScopeRule = MainCoroutineScopeRule()
 * ```
 *
 * @param dispatcher The test coroutine dispatcher to use. Defaults to a new instance
 *                   of [TestCoroutineDispatcher].
 */
@OptIn(ExperimentalCoroutinesApi::class)
class MainCoroutineScopeRule(val dispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher()) :
    TestWatcher(),
    TestCoroutineScope by TestCoroutineScope(dispatcher) {

    /**
     * Overrides the starting method from TestWatcher to set up the test coroutine scope.
     * This method is invoked before each test method is run.
     */
    override fun starting(description: Description?) {
        super.starting(description)
        // If your codebase allows the injection of other dispatchers like
        // Dispatchers.Default and Dispatchers.IO, consider injecting all of them here
        // and renaming this class to `CoroutineScopeRule`
        //
        // All injected dispatchers in a test should point to a single instance of
        // TestCoroutineDispatcher.
        Dispatchers.setMain(dispatcher)
    }

    /**
     * Overrides the finished method from TestWatcher to clean up the test coroutine scope
     * after each test method has finished.
     */
    override fun finished(description: Description?) {
        super.finished(description)
        advanceUntilIdle()
        cleanupTestCoroutines()
        Dispatchers.resetMain()
    }
}