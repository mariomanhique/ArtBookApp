package com.mariomanhique.artbookapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import com.mariomanhique.artbookapp.R
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var fragmentFactory: ArtFragmentFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager.fragmentFactory = fragmentFactory
        setContentView(R.layout.activity_main)

        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)




    }
}

@Entity("test")
data class Test(
    @PrimaryKey
    val id:String,
    val name: String,
    val surname:String
)

data class Test2(
    val Age:String
)