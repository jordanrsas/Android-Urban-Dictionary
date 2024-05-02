package com.cjra.urbandictionary.application.presentation.usecases

import com.cjra.urbandictionary.application.presentation.plain.DefinitionPlain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

class DefineWord constructor(private val source: DefineWordSource) {
    suspend operator fun invoke(word: String): Flow<Result<List<DefinitionPlain>>> =
        source.defineWord(word).map {
            Result.success(it)
        }.catch {
            Result.failure<Throwable>(RuntimeException("Failed to process data"))
        }
}