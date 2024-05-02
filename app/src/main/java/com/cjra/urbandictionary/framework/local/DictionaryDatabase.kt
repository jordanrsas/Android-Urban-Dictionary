package com.cjra.urbandictionary.framework.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.cjra.urbandictionary.framework.local.entity.WordDefinitionEntity

@Database(entities = [WordDefinitionEntity::class], version = 1, exportSchema = false)
abstract class DictionaryDatabase : RoomDatabase() {
    abstract fun getDictionaryDao(): WordDefinitionDao
}