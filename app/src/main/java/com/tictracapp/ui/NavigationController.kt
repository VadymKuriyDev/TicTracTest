package com.tictracapp.ui

import com.tictracapp.ui.fragment.MainFragment
import com.tictracapp.ui.utils.FragmentUtils
import com.tictracappTest.R
import javax.inject.Inject

class NavigationController() {

    private var containerId: Int = R.id.content_container
    lateinit var fragmentManager: androidx.fragment.app.FragmentManager

    @Inject
    constructor(mainActivity: MainActivity) : this() {
        fragmentManager = mainActivity.supportFragmentManager
    }

    fun navigateToMain() {
        if (fragmentManager.findFragmentByTag(MainFragment::class.java.name) == null) {
            FragmentUtils.openContentFragment(fragmentManager, MainFragment.newInstance(), containerId, false, MainFragment::class.java.name, haveTransition = false)
        }
    }
}