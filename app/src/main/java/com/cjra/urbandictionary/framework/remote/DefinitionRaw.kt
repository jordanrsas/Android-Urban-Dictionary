package com.cjra.urbandictionary.framework.remote

import com.cjra.urbandictionary.application.presentation.plain.DefinitionPlain

data class DefinitionRaw(
    val definition: String,
    val permalink: String,
    val thumbs_up: Int,
    val author: String,
    val word: String,
    val defid: Int,
    val current_vote: String?,
    val written_on: String,
    val example: String,
    val thumbs_down: Int
) {
    fun mapToPlain(): DefinitionPlain =
        DefinitionPlain(
            definition = this.definition,
            likes = this.thumbs_up,
            dislikes = this.thumbs_down,
            word = this.word,
            example = this.example
        )

    companion object {
        fun mapDefinitionsRawToPlain(definitionsRaw: List<DefinitionRaw>): List<DefinitionPlain> =
            definitionsRaw.map {
                it.mapToPlain()
            }
    }
}