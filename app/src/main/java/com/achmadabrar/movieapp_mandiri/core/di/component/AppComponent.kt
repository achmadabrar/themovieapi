package com.achmadabrar.movieapp_mandiri.core.di.component

import android.app.Application
import com.achmadabrar.movieapp_mandiri.core.base.BaseApplication
import com.achmadabrar.movieapp_mandiri.core.di.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ViewModelModule::class,
        ActivityBuilderModule::class,
        FragmentBuilderModule::class,
        NetworkModule::class
])
interface AppComponent: AndroidInjector<BaseApplication> {

    @Component.Builder
    interface Builder{

        @BindsInstance
        abstract fun application(application: Application): Builder

        fun build(): AppComponent
    }

}