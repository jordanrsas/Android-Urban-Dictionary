package com.cjra.urbandictionary.framework.local

import com.cjra.urbandictionary.application.data.local.DictionaryDataSourceLocal
import com.cjra.urbandictionary.application.presentation.plain.DefinitionPlain
import com.cjra.urbandictionary.framework.local.entity.WordDefinitionEntity

class DictionaryLocalDataGateway(private val wordDefinitionDao: WordDefinitionDao) :
    DictionaryDataSourceLocal {

    override suspend fun defineWordLocal(word: String): List<DefinitionPlain> {
        val definitionsEntity: List<WordDefinitionEntity> =
            wordDefinitionDao.getWordDefinitions(word)
        return WordDefinitionEntity.mapDefinitionsEntityToPlain(definitionsEntity)
    }

    override suspend fun insertWordDefinitions(definitions: List<DefinitionPlain>) =
        wordDefinitionDao.insertWordDefinitions(
            WordDefinitionEntity.mapDefinitionsPlainToEntity(
                definitions
            )
        )
}