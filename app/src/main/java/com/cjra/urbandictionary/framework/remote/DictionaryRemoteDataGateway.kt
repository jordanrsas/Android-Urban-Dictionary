package com.cjra.urbandictionary.framework.remote

import com.cjra.urbandictionary.application.data.remote.DictionaryDataSourceRemote
import com.cjra.urbandictionary.application.presentation.plain.DefinitionPlain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class DictionaryRemoteDataGateway(private val service: DictionaryService) :
    DictionaryDataSourceRemote {

    override suspend fun defineWord(word: String): Flow<List<DefinitionPlain>> =
        service.defineWord(word).map {
            DefinitionRaw.mapDefinitionsRawToPlain(it)
        }
}