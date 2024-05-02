package com.cjra.urbandictionary.application.data.local

import com.cjra.urbandictionary.application.presentation.plain.DefinitionPlain
import kotlinx.coroutines.flow.Flow


interface DictionaryDataSourceLocal {
    suspend fun defineWord(word: String): Flow<List<DefinitionPlain>>
}