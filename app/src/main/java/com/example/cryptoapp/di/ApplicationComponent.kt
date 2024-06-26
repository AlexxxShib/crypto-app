package com.example.cryptoapp.di

import android.app.Application
import com.example.cryptoapp.presentation.CoinDetailFragment
import com.example.cryptoapp.presentation.CoinPriceListActivity
import com.example.cryptoapp.presentation.CryptoApp
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(
    modules = [
        DataModule::class,
        ViewModelModule::class,
        WorkerModule::class
    ]
)
interface ApplicationComponent {

    fun inject(application: CryptoApp)

    fun inject(activity: CoinPriceListActivity)

    fun inject(fragment: CoinDetailFragment)

    @Component.Factory
    interface ApplicationComponentFactory {

        fun create(
            @BindsInstance
            application: Application
        ): ApplicationComponent
    }
}
