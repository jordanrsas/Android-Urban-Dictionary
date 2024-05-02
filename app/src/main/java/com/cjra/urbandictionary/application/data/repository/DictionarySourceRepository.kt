package com.cjra.urbandictionary.application.data.repository

import com.cjra.urbandictionary.application.data.local.DictionaryDataSourceLocal
import com.cjra.urbandictionary.application.data.remote.DictionaryDataSourceRemote
import com.cjra.urbandictionary.application.presentation.plain.DefinitionPlain
import com.cjra.urbandictionary.application.presentation.usecases.DictionarySource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DictionarySourceRepository(
    private val dictionaryDataSourceRemote: DictionaryDataSourceRemote,
    private val dictionaryDataSourceLocal: DictionaryDataSourceLocal
) : DictionarySource {

    override suspend fun defineWord(word: String): Flow<List<DefinitionPlain>> {
        val localDefinitions: List<DefinitionPlain> =
            dictionaryDataSourceLocal.defineWordLocal(word)
        val remoteDefinitions: Flow<List<DefinitionPlain>> =
            dictionaryDataSourceRemote.defineWord(word)
        return flow {
            val combinedDefinitions = mutableListOf<DefinitionPlain>()

            // Collect remote definitions and handle merging with local definitions
            remoteDefinitions.collect { remote ->
                combinedDefinitions.addAll(remote)
                // Insert remote definitions into local database if local is empty
                if (localDefinitions.isEmpty()) {
                    dictionaryDataSourceLocal.insertWordDefinitions(remote)
                }
                // Emit combined and distinct definitions
                emit(combinedDefinitions.distinctBy { it.definition })
            }
        }
    }
}