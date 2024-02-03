package com.example.cryptoapp.data.worker

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkerParameters
import com.example.cryptoapp.data.database.AppDatabase
import com.example.cryptoapp.data.mapper.CoinMapper
import com.example.cryptoapp.data.network.ApiFactory
import kotlinx.coroutines.delay

class RefreshDataWorker(
    context: Context,
    workerParameters: WorkerParameters
) : CoroutineWorker(context, workerParameters) {

    private val coinInfoDao = AppDatabase.getInstance(context).coinPriceInfoDao()
    private val apiService = ApiFactory.apiService

    private val coinMapper = CoinMapper()

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
}