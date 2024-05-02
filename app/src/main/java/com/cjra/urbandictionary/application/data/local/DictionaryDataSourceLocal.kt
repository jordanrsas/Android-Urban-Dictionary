package com.cjra.urbandictionary.application.data.local

import com.cjra.urbandictionary.application.presentation.plain.DefinitionPlain

interface DictionaryDataSourceLocal {
    suspend fun defineWordLocal(word: String): List<DefinitionPlain>
    suspend fun insertWordDefinitions(definitions: List<DefinitionPlain>)
}