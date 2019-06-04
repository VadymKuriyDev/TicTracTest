package com.tictracapp.dagger

import com.tictracapp.ui.fragment.DetailsFragment
import com.tictracapp.ui.fragment.ImagePreviewFragment
import com.tictracapp.ui.fragment.MainFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentMainBuildersModule {

    @ContributesAndroidInjector
    internal abstract fun contributeBaseInjectableMainFragment(): MainFragment

    @ContributesAndroidInjector
    internal abstract fun contributeDetailsFragment(): DetailsFragment

    @ContributesAndroidInjector
    internal abstract fun contributeImagePreviewFragment(): ImagePreviewFragment
}