package com.example.cryptoapp.data.worker

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import javax.inject.Inject
import javax.inject.Provider

class WorkerFactory @Inject constructor(
    private val workerProviders: @JvmSuppressWildcards Map<Class<out ListenableWorker>, Provider<ChildWorkerFactory>>
) : WorkerFactory() {

    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker? {
        when (workerClassName) {
            RefreshDataWorker::class.qualifiedName -> {
                val workerFactory = workerProviders[RefreshDataWorker::class.java]?.get()
                return workerFactory?.create(appContext, workerParameters)
            }
        }
        return null
    }
}