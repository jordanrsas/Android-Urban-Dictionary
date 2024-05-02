package com.cjra.urbandictionary.framework.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.cjra.urbandictionary.application.presentation.plain.DefinitionPlain

@Entity
data class WordDefinitionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val definition: String,
    val likes: Int,
    val word: String,
    val example: String,
    val dislikes: Int
) {
    fun toDefinitionPlain(): DefinitionPlain =
        DefinitionPlain(
            definition = this.definition,
            likes = this.likes,
            dislikes = this.dislikes,
            word = this.word,
            example = this.example,
        )

    companion object {
        fun mapDefinitionsEntityToPlain(definitionsEntity: List<WordDefinitionEntity>): List<DefinitionPlain> =
            definitionsEntity.map {
                it.toDefinitionPlain()
            }

        fun mapDefinitionsPlainToEntity(definitionPlain: List<DefinitionPlain>): List<WordDefinitionEntity> =
            definitionPlain.map {
                WordDefinitionEntity(
                    definition = it.definition,
                    likes = it.likes,
                    dislikes = it.dislikes,
                    word = it.word,
                    example = it.example,
                )
            }
    }
}