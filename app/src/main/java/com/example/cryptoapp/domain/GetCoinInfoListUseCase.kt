package com.example.cryptoapp.domain

class GetCoinInfoListUseCase(
    private val coinRepository: CoinRepository
) {

    operator fun invoke() = coinRepository.getCoinInfoList()
}