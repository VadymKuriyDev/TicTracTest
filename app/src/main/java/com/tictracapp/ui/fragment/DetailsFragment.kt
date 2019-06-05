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
import com.tictracapp.data.model.UserDetailsData
import com.tictracapp.ui.NavigationController
import com.tictracapp.utils.IntentUtils
import com.tictracapp.viewModel.UserDetailsViewModel
import com.tictracappTest.R
import com.tictracappTest.databinding.UserDetailFragmentBinding
import kotlinx.android.synthetic.main.user_detail_fragment.*
import javax.inject.Inject

class DetailsFragment : Fragment(), Injectable {

    @Inject
    lateinit var navigationMainController: NavigationController
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: UserDetailsViewModel
    private lateinit var binding: UserDetailFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = DataBindingUtil.inflate<UserDetailFragmentBinding>(inflater, R.layout.user_detail_fragment, container, false)
        .also {
            binding = it
        }.root

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(USER_ID, viewModel.userId)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (savedInstanceState?.getInt(USER_ID) ?: arguments?.getInt(USER_ID))?.apply {
            initViewModel(this)
        }
    }

    private fun initViewModel(userId: Int){
        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(UserDetailsViewModel::class.java)
        viewModel.userId = userId
        viewModel.getObservableUserData().observe(viewLifecycleOwner,
            Observer<UserDetailsData> { data ->
                fillView(data)
            })
    }

    private fun fillView(user: UserDetailsData?){
        user?.apply {
            binding.name.text = name
            binding.email.text = email
            binding.phone.text = phone
            Glide.with(this@DetailsFragment)
                .load(profilePicture)
                .into(logo)
            binding.email.setOnClickListener {
                IntentUtils.sendEmail(requireActivity(), email, getString(R.string.default_email_subject))
            }
            binding.phone.setOnClickListener {
                IntentUtils.callToNumber(requireActivity(), phone)
            }
            binding.logo.setOnClickListener {
                navigationMainController.navigateToGallery(viewModel.userId)
            }
        }
    }

    companion object {

        private const val USER_ID = "user"

        fun newInstance(userId: Int) = DetailsFragment().apply {
            arguments = Bundle().apply {
                putInt(USER_ID, userId)
            }
        }
    }
}
