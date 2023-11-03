package com.mariomanhique.artbookapp.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.mariomanhique.artbookapp.MainCoroutineRule
import com.mariomanhique.artbookapp.getOrAwaitValue
import com.mariomanhique.artbookapp.repository.FakeArtRepository
import com.mariomanhique.artbookapp.util.Status
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ArtViewModelTest {

    private lateinit var artViewModel: ArtViewModel


    //It makes sure everything run in the main thread and in order
//    H
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    //It makes sure everything run as if we are in the main thread because we are not going to use the emulator.
    // Note that if we were in the androidTest, we wouldnt need this, because the test run in the main thread.
    @OptIn(ExperimentalCoroutinesApi::class)
    @get: Rule
    var mainCoroutineRule = MainCoroutineRule()




    @Before
    fun setup(){
        //Test Doubles
        artViewModel = ArtViewModel(FakeArtRepository())
    }

    @Test
    fun`insert an art without year returns error`(){
        artViewModel.makeArtValidation(
            name = "Mona Lisa",
            artistName = "Da Vinci",
            artImage = "https://i.ibb.co/jkQG8Dv/Mona-Lisa.jpg",
            year = ""
            )
       val value = artViewModel.insertArtMsg.getOrAwaitValue()
        //We are checking if status is equal to ERROR, so it means that our algorithm work.
        assertThat(value.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun`insert an art without art name returns error`(){
        artViewModel.makeArtValidation(
            name = "",
            artistName = "Da Vinci",
            artImage = "https://i.ibb.co/jkQG8Dv/Mona-Lisa.jpg",
            year = "2020"
        )
        val value = artViewModel.insertArtMsg.getOrAwaitValue()
        assertThat(value.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun`insert an art without artistName returns error`(){
        artViewModel.makeArtValidation(
            name = "Mona Lisa",
            artistName = "",
            artImage = "https://i.ibb.co/jkQG8Dv/Mona-Lisa.jpg",
            year = "2020"
        )
        val value = artViewModel.insertArtMsg.getOrAwaitValue()
        assertEquals(value.status,Status.ERROR)
    }

    @Test
    fun`insert an art without artImage returns error`(){
        artViewModel.makeArtValidation(
            name = "Mona Lisa",
            artistName = "Da Vinci",
            artImage = "",
            year = "2020"
        )
        val value = artViewModel.insertArtMsg.getOrAwaitValue()
        assertEquals(value.status,Status.ERROR)
    }
}