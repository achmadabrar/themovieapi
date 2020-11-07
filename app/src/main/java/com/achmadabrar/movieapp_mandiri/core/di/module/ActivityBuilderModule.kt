package com.achmadabrar.movieapp_mandiri.core.di.module

import com.achmadabrar.movieapp_mandiri.presentation.activity.HomeActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector
    abstract fun contributesHomeActivity(): HomeActivity
}