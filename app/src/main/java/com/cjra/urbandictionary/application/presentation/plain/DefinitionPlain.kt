package com.cjra.urbandictionary.application.presentation.plain

data class DefinitionPlain(
    val definition: String,
    val likes: Int,
    val dislikes: Int,
    val word: String,
    val example: String,
)