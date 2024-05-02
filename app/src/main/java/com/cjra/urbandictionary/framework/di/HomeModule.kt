package com.cjra.urbandictionary.framework.di

import com.cjra.urbandictionary.application.data.remote.DictionaryDataSourceRemote
import com.cjra.urbandictionary.application.data.remote.DictionarySourceRemote
import com.cjra.urbandictionary.application.presentation.HomeStateMapper
import com.cjra.urbandictionary.application.presentation.HomeViewModel
import com.cjra.urbandictionary.application.presentation.usecases.DefineWord
import com.cjra.urbandictionary.application.presentation.usecases.DefineWordSource
import com.cjra.urbandictionary.framework.remote.DictionaryApi
import com.cjra.urbandictionary.framework.remote.DictionaryRemoteDataGateway
import com.cjra.urbandictionary.framework.remote.DictionaryService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val homeModule = module {
    single { DefineWord(get()) }
    single { HomeStateMapper() }

    single<DefineWordSource> { DictionarySourceRemote(get()) }
    single<DictionaryDataSourceRemote> { DictionaryRemoteDataGateway(get()) }

    viewModel { HomeViewModel(get(), get()) }

    single { DictionaryService(get()) }

    single { provideLoggingInterceptor() }
    single { provideHttpClient(get()) }
    single { provideDictionaryApi(get()) }
    single { provideRetrofit(get()) }
}

fun provideLoggingInterceptor(): HttpLoggingInterceptor {
    val interceptor = HttpLoggingInterceptor()
    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
    return interceptor
}

private fun provideHttpClient(interceptor: HttpLoggingInterceptor) =
    OkHttpClient.Builder().readTimeout(20, TimeUnit.SECONDS).connectTimeout(20, TimeUnit.SECONDS)
        .addInterceptor(interceptor).build()

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
    Retrofit.Builder().baseUrl("https://mashape-community-urban-dictionary.p.rapidapi.com/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create()).build()

fun provideDictionaryApi(retrofit: Retrofit): DictionaryApi =
    retrofit.create(DictionaryApi::class.java)