package com.mariomanhique.artbookapp.di

import android.content.Context
import androidx.room.Room
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.mariomanhique.artbookapp.R
import com.mariomanhique.artbookapp.data.ArtBookDatabase
import com.mariomanhique.artbookapp.data.repository.networkRepository.NetworkRepository
import com.mariomanhique.artbookapp.data.repository.networkRepository.NetworkRepositoryInterface
import com.mariomanhique.artbookapp.data.repository.roomRepository.ArtBookRepository
import com.mariomanhique.artbookapp.data.repository.roomRepository.ArtBookRepositoryInterface
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
    fun provideGlide(@ApplicationContext context: Context)
                = Glide.with(context).setDefaultRequestOptions(
                    RequestOptions().placeholder(R.drawable.picture)
                        .error(R.drawable.picture)
                )

    @Singleton
    @Provides
    fun provideNetworkRepository(networkRepository: NetworkRepository):NetworkRepositoryInterface
                = networkRepository

    @Singleton
    @Provides
    fun provideArtRepository(artRepository: ArtBookRepository):ArtBookRepositoryInterface
            = artRepository

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