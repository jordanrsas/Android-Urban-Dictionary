package com.cjra.urbandictionary.home.common

import com.cjra.urbandictionary.application.data.remote.DictionaryDataSourceRemote
import com.cjra.urbandictionary.framework.di.provideDictionaryApi
import com.cjra.urbandictionary.framework.di.provideLoggingInterceptor
import com.cjra.urbandictionary.framework.di.provideRetrofit
import com.cjra.urbandictionary.framework.remote.ApiResponse
import com.cjra.urbandictionary.framework.remote.DictionaryApi
import com.cjra.urbandictionary.framework.remote.DictionaryRemoteDataGateway
import com.cjra.urbandictionary.framework.remote.DictionaryService
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import org.koin.dsl.module

val homeModuleEmpty = module {
    single { DictionaryService(get()) }

    single<DictionaryDataSourceRemote> { DictionaryRemoteDataGateway(get()) }

    single { DictionaryService(get()) }
    single { provideLoggingInterceptor() }
    single { provideDictionaryApi(get()) }
    single { provideRetrofit(get()) }
    single { provideArticlesApiEmpty() }
}

fun provideArticlesApiEmpty(): DictionaryApi = mock {
    onBlocking { defineWord("word") } doReturn ApiResponse(emptyList())
}