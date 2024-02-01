package com.example.cryptoapp.data.network.model

import com.google.gson.annotations.SerializedName

data class CoinNamesListDto(
    @SerializedName("Data")
    var names: ArrayList<CoinNameContainerDto>? = null
)