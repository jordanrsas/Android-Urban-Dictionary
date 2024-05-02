package com.cjra.urbandictionary.framework.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.cjra.urbandictionary.application.presentation.plain.DefinitionPlain

@Entity
data class DefinitionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val definition: String,
    val likes: Int,
    val dislikes: Int,
    val word: String,
    val example: String,
) {
    fun mapToPlain(): DefinitionPlain =
        DefinitionPlain(
            definition = this.definition,
            likes = this.likes,
            dislikes = this.dislikes,
            word = this.word,
            example = this.example
        )

    companion object {
        fun mapDefinitionEntityToPlain(definitionsEntity: List<DefinitionEntity>): List<DefinitionPlain> =
            definitionsEntity.map {
                it.mapToPlain()
            }
    }
}