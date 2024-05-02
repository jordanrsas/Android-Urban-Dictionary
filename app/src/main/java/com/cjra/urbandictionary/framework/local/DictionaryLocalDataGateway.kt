package com.cjra.urbandictionary.framework.local

import com.cjra.urbandictionary.application.data.local.DictionaryDataSourceLocal
import com.cjra.urbandictionary.application.presentation.plain.DefinitionPlain
import kotlinx.coroutines.flow.Flow

class DictionaryLocalDataGateway(private val dataBase: DictionaryDao) : DictionaryDataSourceLocal {
    override suspend fun defineWord(word: String): Flow<List<DefinitionPlain>> =
        dataBase.getDefinitions(word).map {
            it.mapToPlain()
        }
}