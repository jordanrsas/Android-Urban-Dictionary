package com.cjra.urbandictionary.home.common

import com.cjra.urbandictionary.application.data.local.DictionaryDataSourceLocal
import com.cjra.urbandictionary.application.data.remote.DictionaryDataSourceRemote
import com.cjra.urbandictionary.application.data.repository.DictionarySourceRepository
import com.cjra.urbandictionary.application.presentation.HomeStateMapper
import com.cjra.urbandictionary.application.presentation.HomeViewModel
import com.cjra.urbandictionary.application.presentation.usecases.DefineWord
import com.cjra.urbandictionary.application.presentation.usecases.DictionarySource
import com.cjra.urbandictionary.framework.local.DictionaryDatabase
import com.cjra.urbandictionary.framework.local.DictionaryLocalDataGateway
import com.cjra.urbandictionary.framework.local.WordDefinitionDao
import com.cjra.urbandictionary.framework.remote.ApiResponse
import com.cjra.urbandictionary.framework.remote.DictionaryApi
import com.cjra.urbandictionary.framework.remote.DictionaryRemoteDataGateway
import com.cjra.urbandictionary.framework.remote.DictionaryService
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeModuleHappy = module {
    single { DefineWord(get()) }
    single { HomeStateMapper() }

    single<DictionarySource> { DictionarySourceRepository(get(), get()) }

    single { DictionaryService(get()) }
    single<DictionaryDataSourceRemote> { DictionaryRemoteDataGateway(get()) }
    single<DictionaryDataSourceLocal> { DictionaryLocalDataGateway(get()) }

    //single { get<DictionaryDatabase>().getDictionaryDao() }

    viewModel { HomeViewModel(get(), get()) }
    single { provideDictionaryApi() }
    single { createMockDatabase() }
    single { mockedDao }
}

// Mock the DAO methods as needed
val mockedDao: WordDefinitionDao = mock {
    onBlocking {
        it.getWordDefinitions("word")
    } doReturn wordDefinitionsEntity
}

fun createMockDatabase(): DictionaryDatabase = mock {
    val mockDatabase = mock<DictionaryDatabase>()
    val mockDao = mock<WordDefinitionDao>()

    // Define mock behavior for DAO methods
    whenever(mockDatabase.getDictionaryDao()).thenReturn(mockDao)
    // Define mock behavior for getWordDefinitions method
    onBlocking {
        mockDatabase.getDictionaryDao().getWordDefinitions("word")
    } doReturn wordDefinitionsEntity

    return mockDatabase
}

fun provideDictionaryApi(): DictionaryApi = mock {
    onBlocking { defineWord("word") } doReturn ApiResponse(definitionsResultRaw)
}