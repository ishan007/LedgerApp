package com.example.deliveryledger.di.repository.component

import com.example.deliveryledger.di.repository.module.RepositoryModule
import dagger.Subcomponent

@Subcomponent( modules = [RepositoryModule::class])
interface RepositoryComponent