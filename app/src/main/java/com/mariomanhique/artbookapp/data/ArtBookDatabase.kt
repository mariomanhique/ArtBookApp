package com.mariomanhique.artbookapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mariomanhique.artbookapp.model.Art

@Database(entities = [Art::class], version = 1, exportSchema = false)
abstract class ArtBookDatabase: RoomDatabase() {

    abstract fun artBookDao():ArtBookDao
}