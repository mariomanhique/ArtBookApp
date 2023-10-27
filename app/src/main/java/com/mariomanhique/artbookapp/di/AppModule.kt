package com.mariomanhique.artbookapp.di

import android.content.Context
import androidx.room.Room
import com.mariomanhique.artbookapp.data.ArtBookDao
import com.mariomanhique.artbookapp.data.ArtBookDatabase
import com.mariomanhique.artbookapp.utils.Constants.ARTBOOK_DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideArtBookDao(artBookDatabase: ArtBookDatabase): ArtBookDao
                = artBookDatabase.artBookDao()

    @Singleton
    @Provides
    fun provideRoomDatabase(@ApplicationContext context: Context): ArtBookDatabase{
        return Room.databaseBuilder(
            context = context,
            klass = ArtBookDatabase::class.java,
            name = ARTBOOK_DATABASE_NAME
        ).fallbackToDestructiveMigration().build()
    }
}