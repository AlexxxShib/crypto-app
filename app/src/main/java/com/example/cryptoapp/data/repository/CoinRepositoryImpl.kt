package com.example.cryptoapp.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import com.example.cryptoapp.data.database.AppDatabase
import com.example.cryptoapp.data.mapper.CoinMapper
import com.example.cryptoapp.data.worker.RefreshDataWorker
import com.example.cryptoapp.domain.CoinInfo
import com.example.cryptoapp.domain.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val application: Application
) : CoinRepository {

    private val coinInfoDao = AppDatabase.getInstance(application).coinPriceInfoDao()
    private val coinMapper = CoinMapper()

    override fun getCoinInfoList(): LiveData<List<CoinInfo>> {
        return coinInfoDao.getPriceList().map {
            it.map {
                coinMapper.mapDbModelToEntity(it)
            }
        }
    }

    override fun getCoinInfo(fromSymbol: String): LiveData<CoinInfo> {
        return coinInfoDao.getPriceInfoAboutCoin(fromSymbol).map {
            coinMapper.mapDbModelToEntity(it)
        }
    }

    override fun loadData() {
        WorkManager.getInstance(application)
            .enqueueUniqueWork(
                RefreshDataWorker.WORK_NAME,
                ExistingWorkPolicy.REPLACE,
                RefreshDataWorker.makeRequest()
            )
    }
}