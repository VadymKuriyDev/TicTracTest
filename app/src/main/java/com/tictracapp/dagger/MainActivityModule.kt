package com.tictracapp.dagger

import com.tictracapp.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityModule {
    @ContributesAndroidInjector(modules = [(FragmentMainBuildersModule::class)])
    internal abstract fun contributeMainActivity(): MainActivity
}
