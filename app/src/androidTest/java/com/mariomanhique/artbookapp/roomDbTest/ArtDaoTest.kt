package com.mariomanhique.artbookapp.roomDbTest

import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.annotation.ExperimentalTestApi
import androidx.test.core.app.ApplicationProvider
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.mariomanhique.artbookapp.data.ArtBookDao
import com.mariomanhique.artbookapp.data.ArtBookDatabase
import com.mariomanhique.artbookapp.getOrAwaitValue
import com.mariomanhique.artbookapp.model.Art
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named


@SmallTest
@ExperimentalCoroutinesApi
@HiltAndroidTest
class ArtDaoTest {

   @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    @Named("testDatabase")
    lateinit var artDatabase: ArtBookDatabase

    private lateinit var artDao: ArtBookDao

    @Before
    fun setup(){
//        artDatabase = Room.inMemoryDatabaseBuilder(
//            ApplicationProvider.getApplicationContext(),
//            ArtBookDatabase::class.java
//        ).allowMainThreadQueries().build() //allowMainThreadQueries is used only for testing
        hiltRule.inject()
        artDao = artDatabase.artBookDao()
    }


    @After
    fun teardown(){
        artDatabase.close()
    }


    @Test
    fun insertArtTesting() = runTest{
      val art =  Art(
            id = 2,
            name = "Mona Lisa",
            artImage = "test.com",
            artistName = "Da Vinci",
            year = 1990
      )

        artDao.insertArt(art)

       val list = artDao.getAllArts().getOrAwaitValue()
        Log.d("Test", "insertArtTesting:$list ")
        assertThat(list).contains(art)
    }

    @Test
    fun deleteArt() = runTest {
        val art=  Art(
            id = 2,
            name = "Mona Lisa",
            artImage = "test.com",
            artistName = "Da Vinci",
            year = 1990)
        artDao.insertArt(art)
        artDao.deleteArts(art)

        val list = artDao.getAllArts().getOrAwaitValue()
        assertThat(list).doesNotContain(art)


    }
}