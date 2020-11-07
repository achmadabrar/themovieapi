package com.achmadabrar.movieapp_mandiri.core.base

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.HasSupportFragmentInjector
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

open class BaseFragment: Fragment(), HasSupportFragmentInjector {

    protected val disposables: CompositeDisposable = CompositeDisposable()

    @Inject
    protected lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject
    protected lateinit var childFragmentInjector: DispatchingAndroidInjector<Fragment>

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onStop() {
        disposables.clear()
        super.onStop()
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return childFragmentInjector
    }
}