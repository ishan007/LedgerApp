package com.example.deliveryledger.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.deliveryledger.di.view.ActivityScope
import javax.inject.Inject
import javax.inject.Provider


@Suppress("UNCHECKED_CAST")
@ActivityScope
class ViewModelProviderFactory @Inject constructor(private val viewModels: MutableMap<Class<out ViewModel>, Provider<ViewModel>>) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T = viewModels[modelClass]?.get() as T
}