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
import com.bumptech.glide.request.RequestOptions
import com.tictracapp.dagger.Injectable
import com.tictracapp.data.model.User
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
        outState.putParcelable(USER, viewModel.getUserData())
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (savedInstanceState != null) {
            savedInstanceState.getParcelable<User>(USER)
        } else {
            arguments?.getParcelable(USER)
        }?.apply {
            initViewModel(this)
        }
    }

    private fun initViewModel(user: User){
        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(UserDetailsViewModel::class.java)
        viewModel.getObservableUserData().observe(viewLifecycleOwner,
            Observer<User> { data ->
                fillView(data)
            })
        viewModel.loadData(user)
    }

    private fun fillView(user: User?){
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
                navigationMainController.navigateToGallery(profilePicture)
            }
        }
    }

    companion object {

        private const val USER = "user"

        fun newInstance(user: User) = DetailsFragment().apply {
            arguments = Bundle().apply {
                putParcelable(USER, user)
            }
        }
    }
}
