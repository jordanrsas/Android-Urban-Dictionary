package com.cjra.urbandictionary.home.common

import androidx.room.Room
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
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeModuleEmpty = module {
    single { DefineWord(get()) }
    single { HomeStateMapper() }

    single<DictionarySource> { DictionarySourceRepository(get(), get()) }

    single { DictionaryService(get()) }
    single<DictionaryDataSourceRemote> { DictionaryRemoteDataGateway(get()) }
    single<DictionaryDataSourceLocal> { DictionaryLocalDataGateway(get()) }

    //single { get<DictionaryDatabase>().getDictionaryDao() }

    viewModel { HomeViewModel(get(), get()) }
    single { provideDictionaryApiEmpty() }
    single { createMockDatabase() }
    single { mockedDao }
}

fun provideDictionaryApiEmpty(): DictionaryApi = mock {
    onBlocking { defineWord("word") } doReturn ApiResponse(emptyList())
}
