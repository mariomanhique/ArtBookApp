package com.mariomanhique.artbookapp.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isGone
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.mariomanhique.artbookapp.R
import com.mariomanhique.artbookapp.adapter.ImageRecyclerAdapter
import com.mariomanhique.artbookapp.databinding.FragmentImageApiBinding
import com.mariomanhique.artbookapp.util.Status
import com.mariomanhique.artbookapp.viewModel.ImagesViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject


class ImageApiFragment @Inject constructor(
    val imageRecyclerAdapter: ImageRecyclerAdapter
) : Fragment(R.layout.fragment_image_api) {

    lateinit var viewModel: ImagesViewModel
    private var fragmentBinding: FragmentImageApiBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[ImagesViewModel::class.java]

        val binding = FragmentImageApiBinding.bind(view)
        fragmentBinding = binding

        var job: Job? = null
        binding.searchText.addTextChangedListener {
            job?.cancel()
            job = lifecycleScope.launch {
                delay(1000L)
                it?.let {
                   if (it.toString().isNotEmpty()){
                       viewModel.searchImage(it.toString())
                   }
                }
            }
        }

        subscribeToObservers()

            binding.imageRecyclerView.adapter = imageRecyclerAdapter
        binding.imageRecyclerView.layoutManager = GridLayoutManager(requireContext(),3)
        imageRecyclerAdapter.setOnItemClickListener {selectedImage ->
            viewModel.setSelectedImage(selectedImage)

            findNavController().previousBackStackEntry?.savedStateHandle?.set("selectedImage", selectedImage)
            findNavController().popBackStack() // Pops the back stack

//            viewModel.selectedImage.observe(viewLifecycleOwner, Observer {
//
//            findNavController().popBackStack() // Pops the back stack
//            findNavController().previousBackStackEntry?.savedStateHandle?.set("selectedImage", selectedImage)
//            })
        }
    }

    private fun subscribeToObservers(){
        viewModel.images.observe(viewLifecycleOwner, Observer {
            when(it.status){
                Status.SUCCESS -> {
                    val urls = it.data?.hits?.map {imageResult->
                        imageResult.largeImageURL
                    }
                    imageRecyclerAdapter.images = urls ?: listOf()

                    fragmentBinding?.progressBar?.visibility = View.GONE
                }

                Status.ERROR -> {
                    Toast.makeText(requireContext(),it.message?:"Error",Toast.LENGTH_LONG).show()
                    fragmentBinding?.progressBar?.visibility = View.GONE

                }
                Status.LOADING -> {
                    fragmentBinding?.progressBar?.visibility = View.VISIBLE

                }

            }
        })
    }
}