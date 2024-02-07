package com.example.dependencyinjectionstart.example2.di

import com.example.cryptoapp.data.repository.CoinRepositoryImpl
import com.example.cryptoapp.domain.CoinRepository
import dagger.Binds
import dagger.Module

@Module
interface DataModule {

    @Binds
    fun bindRepository(impl: CoinRepositoryImpl): CoinRepository

}
