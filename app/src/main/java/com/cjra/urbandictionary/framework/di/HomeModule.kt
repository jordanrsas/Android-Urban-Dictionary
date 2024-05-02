package com.cjra.urbandictionary.framework.di

import android.app.Application
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

    single<DictionarySource> { DictionarySourceRepository(get(), get()) }
    single<DictionaryDataSourceRemote> { DictionaryRemoteDataGateway(get()) }
    single<DictionaryDataSourceLocal> { DictionaryLocalDataGateway(get()) }

    viewModel { HomeViewModel(get(), get()) }

    single { DictionaryService(get()) }

    single { provideLoggingInterceptor() }
    single { provideHttpClient(get()) }
    single { provideDictionaryApi(get()) }
    single { provideRetrofit(get()) }
    single { provideDataBase(get()) }
    single { provideDao(get()) }
}

fun provideDataBase(application: Application): DictionaryDatabase =
    Room.databaseBuilder(
        application,
        DictionaryDatabase::class.java,
        "table_definitions"
    ).fallbackToDestructiveMigration().build()

fun provideDao(dictionaryDatabase: DictionaryDatabase): WordDefinitionDao =
    dictionaryDatabase.getDictionaryDao()

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