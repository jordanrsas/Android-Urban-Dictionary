package com.cjra.urbandictionary.home.common

import com.cjra.urbandictionary.application.data.remote.DictionaryDataSourceRemote
import com.cjra.urbandictionary.application.data.remote.DictionarySourceRemote
import com.cjra.urbandictionary.application.presentation.HomeStateMapper
import com.cjra.urbandictionary.application.presentation.usecases.DefineWord
import com.cjra.urbandictionary.application.presentation.usecases.DefineWordSource
import com.cjra.urbandictionary.framework.remote.DictionaryApi
import com.cjra.urbandictionary.framework.remote.DictionaryRemoteDataGateway
import com.cjra.urbandictionary.framework.remote.DictionaryService
import com.nhaarman.mockitokotlin2.doThrow
import com.nhaarman.mockitokotlin2.mock
import org.koin.dsl.module

val homeModuleError = module {
    single { DefineWord(get()) }
    single { HomeStateMapper() }

    single<DefineWordSource> { DictionarySourceRemote(get()) }


    single { DictionaryService(get()) }
    single<DictionaryDataSourceRemote> { DictionaryRemoteDataGateway(get()) }

    //single { provideLoggingInterceptor() }
    //single { provideDictionaryApi(get()) }
    //single { provideRetrofit(get()) }
    single { provideDictionaryApiError() }
}

fun provideDictionaryApiError(): DictionaryApi = mock {
    onBlocking { defineWord("word") } doThrow RuntimeException("network error")
}