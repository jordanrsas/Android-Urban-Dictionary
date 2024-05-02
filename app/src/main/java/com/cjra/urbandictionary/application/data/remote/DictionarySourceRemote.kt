package com.cjra.urbandictionary.application.data.remote

import com.cjra.urbandictionary.application.presentation.plain.DefinitionPlain
import com.cjra.urbandictionary.application.presentation.usecases.DefineWordSource
import kotlinx.coroutines.flow.Flow

class DictionarySourceRemote constructor(
    private val dictionaryDataSourceRemote: DictionaryDataSourceRemote
) :
    DefineWordSource {

    override suspend fun defineWord(word: String): Flow<List<DefinitionPlain>> {
        val definitions = dictionaryDataSourceRemote.defineWord(word)
        return definitions
    }
}