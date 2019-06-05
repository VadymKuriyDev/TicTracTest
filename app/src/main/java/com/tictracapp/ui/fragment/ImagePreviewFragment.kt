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
import com.tictracapp.viewModel.ImagePreviewViewModel
import com.tictracappTest.R
import com.tictracappTest.databinding.ImagePreviewGalleryBinding
import javax.inject.Inject

class ImagePreviewFragment : Fragment(), Injectable {

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

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(USER_ID, viewModel.userId)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (savedInstanceState != null) {
            savedInstanceState.getInt(USER_ID)
        } else {
            arguments?.getInt(USER_ID)
        }?.apply {
            initViewModel(this)
        }
    }

    private fun initViewModel(userId: Int){
        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(ImagePreviewViewModel::class.java)
        viewModel.userId = userId
        viewModel.getObservableUserLogo().observe(viewLifecycleOwner,
            Observer<String> { data ->
                fillView(data)
            })
    }

    private fun fillView(logo: String?){
        logo?.apply {
            Glide.with(this@ImagePreviewFragment)
                .load(logo)
                .into(binding.image)
        }
    }

    companion object {

        private const val USER_ID = "user_id"

        fun newInstance(userId: Int) = ImagePreviewFragment().apply {
            arguments = Bundle().apply {
                putInt(USER_ID, userId)
            }
        }
    }
}
