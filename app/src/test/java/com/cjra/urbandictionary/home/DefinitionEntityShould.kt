package com.cjra.urbandictionary.home

import com.cjra.urbandictionary.application.presentation.Definition
import com.cjra.urbandictionary.home.common.definitions
import com.cjra.urbandictionary.home.common.definitionsPlain
import org.junit.Test
import kotlin.test.assertEquals

class DefinitionEntityShould {

    @Test
    fun mapDefinitionFromDefinitionData() {
        val actual = Definition.mapDefinitionsFromData(definitionsPlain)
        assertEquals(definitions, actual)
    }
}