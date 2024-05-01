package com.cjra.urbandictionary.application.data.remote

import com.cjra.urbandictionary.application.presentation.plain.DefinitionPlain
import kotlinx.coroutines.flow.Flow

interface DictionaryDataSourceRemote {
    suspend fun defineWord(word: String): Flow<List<DefinitionPlain>>
}