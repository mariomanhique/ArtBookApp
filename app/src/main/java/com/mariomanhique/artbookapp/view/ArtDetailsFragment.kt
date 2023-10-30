package com.mariomanhique.artbookapp.view

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.RequestManager
import com.mariomanhique.artbookapp.R
import com.mariomanhique.artbookapp.databinding.FragmentArtDetailsBinding
import com.mariomanhique.artbookapp.viewModel.ArtViewModel
import javax.inject.Inject


class ArtDetailsFragment @Inject constructor(
    val glide: RequestManager
) : Fragment(R.layout.fragment_art_details) {

    lateinit var artViewModel: ArtViewModel

    private var artDetailsFragment: FragmentArtDetailsBinding? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        artViewModel = ViewModelProvider(requireActivity())[ArtViewModel::class.java]

        val binding = FragmentArtDetailsBinding.bind(view)
        artDetailsFragment = binding


        binding.addImageButton.setOnClickListener {
            findNavController().navigate(ArtDetailsFragmentDirections.actionArtDetailsFragment2ToImageApiFragment())
        }

        binding.button.setOnClickListener  {
            val name = binding.nameEditText.text.toString()
            val artistName = binding.artistEditText.text.toString()
            val year = binding.yearEditText.text.toString()
            artViewModel.makeArtValidation(
                name = name,
                artImage = artistName,
                year = year,
                artistName = artistName

            )
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