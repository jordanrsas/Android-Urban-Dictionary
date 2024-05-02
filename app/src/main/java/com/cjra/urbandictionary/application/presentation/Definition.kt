package com.cjra.urbandictionary.application.presentation

import com.cjra.urbandictionary.application.presentation.plain.DefinitionPlain

data class Definition(
    val definition: String,
    val likes: Int,
    val dislikes: Int,
    val word: String,
    val example: String
) {
    companion object {
        fun mapDefinitionsFromData(data: List<DefinitionPlain>): List<Definition> =
            data.map {
                mapDefinition(it)
            }

        private fun mapDefinition(definitionPlain: DefinitionPlain): Definition =
            Definition(
                definition = definitionPlain.definition,
                word = definitionPlain.word,
                likes = definitionPlain.likes,
                dislikes = definitionPlain.dislikes,
                example = definitionPlain.example
            )
    }
}