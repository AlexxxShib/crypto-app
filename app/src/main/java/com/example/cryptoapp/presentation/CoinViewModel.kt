package com.example.cryptoapp.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.cryptoapp.data.repository.CoinRepositoryImpl
import com.example.cryptoapp.domain.GetCoinInfoListUseCase
import com.example.cryptoapp.domain.GetCoinInfoUseCase
import com.example.cryptoapp.domain.LoadDataUseCase
import javax.inject.Inject

class CoinViewModel @Inject constructor(
    private val getCoinInfoListUseCase : GetCoinInfoListUseCase,
    private val getCoinInfoUseCase : GetCoinInfoUseCase,
    private val loadDataUseCase : LoadDataUseCase
) : ViewModel() {

    val coinInfoList = getCoinInfoListUseCase.invoke()

    init {
        loadDataUseCase.invoke()
    }

    fun getDetailInfo(fSym: String) = getCoinInfoUseCase.invoke(fSym)

}