package com.mariomanhique.artbookapp.view

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.RequestManager
import com.mariomanhique.artbookapp.R
import com.mariomanhique.artbookapp.databinding.FragmentArtDetailsBinding
import com.mariomanhique.artbookapp.util.Status
import com.mariomanhique.artbookapp.viewModel.ArtViewModel
import javax.inject.Inject


class ArtDetailsFragment @Inject constructor(
    val glide: RequestManager
) : Fragment(R.layout.fragment_art_details) {

    lateinit var artViewModel: ArtViewModel


    private var artDetailsFragmentBinding: FragmentArtDetailsBinding? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        artViewModel = ViewModelProvider(requireActivity())[ArtViewModel::class.java]

        val binding = FragmentArtDetailsBinding.bind(view)
        artDetailsFragmentBinding = binding

        binding.addImageButton.setOnClickListener {
            findNavController().navigate(ArtDetailsFragmentDirections.actionArtDetailsFragment2ToImageApiFragment())
        }


        var imageUrl: String? = null

        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<String>("selectedImage")
            ?.observe(viewLifecycleOwner) { selectedImage ->
                Log.d("Observer", "Observer triggered with Image URL: $imageUrl")
                Toast.makeText(requireContext(), "Image URL: $imageUrl", Toast.LENGTH_LONG).show()
                glide.load(selectedImage).into(binding.imageViewer)
                imageUrl = selectedImage
            }

        binding.button.setOnClickListener  {
            val name = binding.nameEditText.text.toString()
            val artistName = binding.artistEditText.text.toString()
            val year = binding.yearEditText.text.toString()

            artViewModel.makeArtValidation(
                name = name,
                artImage = imageUrl?:"",
                year = year,
                artistName = artistName

            )
        }

        subScribeToObservers()




        val callBack = object : OnBackPressedCallback(enabled = true){
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }

        }

        requireActivity().onBackPressedDispatcher.addCallback(callBack)
    }

    fun subScribeToObservers(){
        artViewModel.insertArtMsg.observe(viewLifecycleOwner, Observer {
            when(it.status){
                Status.SUCCESS -> {
                    Toast.makeText(requireContext(),"Success",Toast.LENGTH_SHORT).show()
                    findNavController().popBackStack()
                    artViewModel.resetInsertArtMsg()
                }
                Status.ERROR -> {
                    Toast.makeText(requireContext(),"${it.message}",Toast.LENGTH_SHORT).show()
                }
                Status.LOADING -> {

                }
            }
        })
    }


    override fun onDestroy() {
        artDetailsFragmentBinding = null
        super.onDestroy()
    }
}