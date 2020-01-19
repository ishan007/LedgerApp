package com.example.deliveryledger.di.repository.module

import dagger.Module


@Module(includes = [DomainModule::class, StorageModule::class, NetworkModule::class])
class RepositoryModule

