package com.tictracapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.tictracapp.dagger.Injectable
import com.tictracapp.ui.NavigationController
import com.tictracapp.viewModel.ImagePreviewViewModel
import com.tictracappTest.R
import com.tictracappTest.databinding.ImagePreviewGalleryBinding
import javax.inject.Inject

class ImagePreviewFragment : Fragment(), Injectable {

    @Inject
    lateinit var navigationMainController: NavigationController
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: ImagePreviewViewModel
    private lateinit var binding: ImagePreviewGalleryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = DataBindingUtil.inflate<ImagePreviewGalleryBinding>(inflater, R.layout.image_preview_gallery, container, false)
        .also {
            binding = it
        }.root

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViewModel()
    }

    private fun initViewModel(){
        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(ImagePreviewViewModel::class.java)
        viewModel.getObservableUserData().observe(viewLifecycleOwner,
            Observer<String> { data ->
                fillView(data)
            })
        arguments?.getString(LOGO)?.also{
            viewModel.loadData(it)
        }
    }

    private fun fillView(logo: String?){
        logo?.apply {
            Glide.with(this@ImagePreviewFragment)
                .load(logo)
                .into(binding.image)
        }
    }

    companion object {

        private const val LOGO = "logo"

        fun newInstance(logo: String) = ImagePreviewFragment().apply {
            arguments = Bundle().apply {
                putString(LOGO, logo)
            }
        }
    }
}
