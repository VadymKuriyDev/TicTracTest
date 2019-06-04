package com.tictracapp.ui.fragment

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.tictracapp.dagger.Injectable
import com.tictracapp.data.model.User
import com.tictracapp.ui.NavigationController
import com.tictracapp.ui.adapter.UsersAdapter
import com.tictracapp.ui.adapter.UsersListener
import com.tictracapp.viewModel.MainViewModel

import com.tictracappTest.R
import com.tictracappTest.databinding.MainFragmentBinding
import timber.log.Timber
import javax.inject.Inject

class MainFragment : Fragment(), UsersListener, Injectable{

    @Inject
    lateinit var navigationMainController: NavigationController
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: MainViewModel
    private lateinit var binding: MainFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = DataBindingUtil.inflate<MainFragmentBinding>(inflater, R.layout.main_fragment, container, false).also {
        binding = it
        binding.apply {
            list.adapter = UsersAdapter(this@MainFragment)
            list.layoutManager = LinearLayoutManager(context, VERTICAL, false)
        }
    }.root

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViewModel()
    }

    private fun initViewModel(){
        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(MainViewModel::class.java)
        viewModel.getObservableUsersData().observe(viewLifecycleOwner,
            Observer<List<User>> { data ->
                data?.let {
                    (binding.list.adapter as UsersAdapter).updateData(it)
                }
            })
    }

    override fun userSelected(user: User) {
        Timber.d("User selected ${user.name}")
        navigationMainController.navigateToDetails(user)
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}
