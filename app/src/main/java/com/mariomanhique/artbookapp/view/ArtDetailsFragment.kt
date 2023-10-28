package com.mariomanhique.artbookapp.view

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
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

        val callBack = object : OnBackPressedCallback(enabled = true){
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }

        }

        requireActivity().onBackPressedDispatcher.addCallback(callBack)
    }


    override fun onDestroy() {
        artDetailsFragment = null
        super.onDestroy()
    }
}