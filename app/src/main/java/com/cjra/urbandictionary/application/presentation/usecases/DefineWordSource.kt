package com.cjra.urbandictionary.application.presentation.usecases

import com.cjra.urbandictionary.application.presentation.plain.DefinitionPlain
import kotlinx.coroutines.flow.Flow

interface DefineWordSource {
    suspend fun defineWord(word: String): Flow<List<DefinitionPlain>>
}