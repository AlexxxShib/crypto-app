package com.example.cryptoapp.data.worker

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.ListenableWorker
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkerParameters
import com.example.cryptoapp.data.database.CoinInfoDao
import com.example.cryptoapp.data.mapper.CoinMapper
import com.example.cryptoapp.data.network.ApiService
import kotlinx.coroutines.delay
import javax.inject.Inject

class RefreshDataWorker (
    context: Context,
    workerParameters: WorkerParameters,
    private val coinInfoDao: CoinInfoDao,
    private val apiService: ApiService,
    private val coinMapper: CoinMapper
) : CoroutineWorker(context, workerParameters) {

    override suspend fun doWork(): Result {
        while (true) {
            try {
                val topCoins = apiService.getTopCoinsInfo(limit = 50)
                val fSyms = coinMapper.mapNamesListToString(topCoins)
                val jsonContainer = apiService.getFillPriceList(fSyms = fSyms)
                val listCoinInfo = coinMapper.mapJsonContainerToListCoinInfo(jsonContainer).map {
                    coinMapper.mapDtoToDbModel(it)
                }
                coinInfoDao.insertPriceList(listCoinInfo)
            } catch (e: Exception) {
                Log.e("DATA_LAYER", "Loading data error!", e)
            }

            delay(10 * 1000)
        }
    }

    companion object {

        const val WORK_NAME = "RefreshDataWorker"

        fun makeRequest() = OneTimeWorkRequestBuilder<RefreshDataWorker>()
//            .setConstraints(
//                Constraints.Builder()
//                    .setRequiresCharging(true)
//                    .build()
//            )
            .build()
    }

    class Factory @Inject constructor(
        private val coinInfoDao: CoinInfoDao,
        private val apiService: ApiService,
        private val coinMapper: CoinMapper
    ): ChildWorkerFactory {

        override fun create(
            context: Context,
            workerParameters: WorkerParameters
        ): ListenableWorker {
            return RefreshDataWorker(
                context,
                workerParameters,
                coinInfoDao,
                apiService,
                coinMapper
            )
        }
    }

}