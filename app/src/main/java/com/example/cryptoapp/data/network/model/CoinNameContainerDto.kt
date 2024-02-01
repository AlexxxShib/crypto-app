package com.example.cryptoapp.data.network.model

import com.google.gson.annotations.SerializedName

data class CoinNameContainerDto(
    @SerializedName("CoinInfo")
    var coinName: CoinNameDto? = CoinNameDto()
)