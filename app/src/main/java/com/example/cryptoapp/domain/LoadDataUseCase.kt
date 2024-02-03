package com.example.cryptoapp.domain

class LoadDataUseCase(
    private val coinRepository: CoinRepository
) {

    operator fun invoke() {
        coinRepository.loadData()
    }
}