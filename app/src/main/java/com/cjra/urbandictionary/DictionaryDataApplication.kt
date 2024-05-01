package com.cjra.urbandictionary

import android.app.Application
import com.cjra.urbandictionary.framework.di.homeModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class DictionaryDataApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@DictionaryDataApplication)
            modules(listOf(homeModule))
        }
    }
}