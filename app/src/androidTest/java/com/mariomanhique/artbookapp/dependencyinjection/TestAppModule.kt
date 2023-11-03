package com.mariomanhique.artbookapp.dependencyinjection

import android.content.Context
import androidx.room.Room
import com.mariomanhique.artbookapp.data.ArtBookDao
import com.mariomanhique.artbookapp.data.ArtBookDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object TestAppModule {

    @Provides
    @Named("testDatabase") //We have to do this so hilt can know the right Room to inject to avoid confusion and cycles
    fun injectInMemoryRoom(@ApplicationContext context: Context)
                = Room.inMemoryDatabaseBuilder(
                        context = context,
                        ArtBookDatabase::class.java
                    ).allowMainThreadQueries().build()
}