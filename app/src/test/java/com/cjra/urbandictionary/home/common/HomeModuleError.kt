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
import com.cjra.urbandictionary.framework.remote.DictionaryApi
import com.cjra.urbandictionary.framework.remote.DictionaryRemoteDataGateway
import com.cjra.urbandictionary.framework.remote.DictionaryService
import com.nhaarman.mockitokotlin2.doThrow
import com.nhaarman.mockitokotlin2.mock
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeModuleError = module {
    single { DefineWord(get()) }
    single { HomeStateMapper() }

    single<DictionarySource> { DictionarySourceRepository(get(), get()) }

    //single { get<DictionaryDatabase>().getDictionaryDao() }

    single { DictionaryService(get()) }
    single<DictionaryDataSourceRemote> { DictionaryRemoteDataGateway(get()) }
    single<DictionaryDataSourceLocal> { DictionaryLocalDataGateway(get()) }

    viewModel { HomeViewModel(get(), get()) }
    single { provideDictionaryApiError() }
    single { createMockDatabase() }
    single { mockedDao }
}

fun provideDictionaryApiError(): DictionaryApi = mock {
    onBlocking { defineWord("word") } doThrow RuntimeException("network error")
}