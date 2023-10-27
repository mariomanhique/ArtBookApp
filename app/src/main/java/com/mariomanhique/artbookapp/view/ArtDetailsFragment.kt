package com.mariomanhique.artbookapp.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.mariomanhique.artbookapp.R
import com.mariomanhique.artbookapp.databinding.FragmentArtDetailsBinding


class ArtDetailsFragment: Fragment(R.layout.fragment_art_details) {

    private var artDetailsFragment: FragmentArtDetailsBinding? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentArtDetailsBinding.bind(view)
        artDetailsFragment = binding


        binding.addImageButton.setOnClickListener {
            findNavController().navigate(ArtDetailsFragmentDirections.actionArtDetailsFragment2ToImageApiFragment())
        }

        binding.button.setOnClickListener  {

        }
    }


    override fun onDestroy() {
        artDetailsFragment = null
        super.onDestroy()
    }
}