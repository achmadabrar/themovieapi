package com.achmadabrar.movieapp_mandiri.core.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel

abstract class BaseViewModel<V>: ViewModel() {
    protected val disposables: CompositeDisposable = CompositeDisposable()

    protected val ioScope = CoroutineScope(Dispatchers.Default)

    override fun onCleared() {
        disposables.clear()
        super.onCleared()
        ioScope.coroutineContext.cancel()
    }
}