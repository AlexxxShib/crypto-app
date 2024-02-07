package com.example.cryptoapp.presentation

import android.app.Application
import androidx.work.Configuration
import com.example.cryptoapp.data.worker.WorkerFactory
import com.example.cryptoapp.di.DaggerApplicationComponent
import javax.inject.Inject

class CryptoApp : Application(), Configuration.Provider {

    @Inject
    lateinit var workerFactory: WorkerFactory

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }

    override val workManagerConfiguration by lazy {
        Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()
    }

    override fun onCreate() {
        component.inject(this)
        super.onCreate()
    }
}