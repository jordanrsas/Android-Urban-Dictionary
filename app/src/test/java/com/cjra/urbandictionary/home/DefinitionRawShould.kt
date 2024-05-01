package com.cjra.urbandictionary.home

import com.cjra.urbandictionary.application.presentation.plain.DefinitionPlain
import com.cjra.urbandictionary.framework.remote.DefinitionRaw
import com.cjra.urbandictionary.home.common.BaseUnitTestHome
import com.cjra.urbandictionary.home.common.definitionsPlain
import com.cjra.urbandictionary.home.common.definitionsResultRaw
import org.junit.Test
import kotlin.test.assertEquals

class DefinitionRawShould : BaseUnitTestHome() {

    @Test
    fun mapDefinitionsRawToPlain() {
        val actual = DefinitionRaw.mapDefinitionsRawToPlain(definitionsResultRaw)
        assertEquals(definitionsPlain, actual)
    }

    @Test
    fun mapEmptyDefinitionsRawToEmptyPlain() {
        val actual = DefinitionRaw.mapDefinitionsRawToPlain(emptyList())
        assertEquals(emptyList(), actual)
    }

    @Test
    fun mapNullDefinitionsRawToEmptyPlain() {
        val actual = DefinitionRaw.mapDefinitionsRawToPlain(emptyList())
        assertEquals(emptyList(), actual)
    }
}