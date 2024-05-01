package com.cjra.urbandictionary.framework.di

import com.cjra.urbandictionary.framework.remote.DictionaryApi
import com.cjra.urbandictionary.framework.remote.DictionaryService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val homeModule = module {
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