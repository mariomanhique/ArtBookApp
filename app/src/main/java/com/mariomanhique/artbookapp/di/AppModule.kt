package com.mariomanhique.artbookapp.di

import android.content.Context
import androidx.room.Room
import com.mariomanhique.artbookapp.data.ArtBookDatabase
import com.mariomanhique.artbookapp.network.ImagesApi
import com.mariomanhique.artbookapp.util.Constants.ARTBOOK_DATABASE_NAME
import com.mariomanhique.artbookapp.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideArtApi(): ImagesApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ImagesApi::class.java)
    }

    @Singleton
    @Provides
    fun provideArtBookDao(artBookDatabase: ArtBookDatabase)
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