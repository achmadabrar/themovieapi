package com.achmadabrar.movieapp_mandiri.core.di.module

import com.achmadabrar.movieapp_mandiri.presentation.fragment.DetailPageFragment
import com.achmadabrar.movieapp_mandiri.presentation.fragment.GenrePageFragment
import com.achmadabrar.movieapp_mandiri.presentation.fragment.ListMoviePageFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilderModule {

    @ContributesAndroidInjector
    abstract fun contributesGenreFragment(): GenrePageFragment

    @ContributesAndroidInjector
    abstract fun contributesDetailFragment(): DetailPageFragment

    @ContributesAndroidInjector
    abstract fun contributesListMovieFragment(): ListMoviePageFragment

}