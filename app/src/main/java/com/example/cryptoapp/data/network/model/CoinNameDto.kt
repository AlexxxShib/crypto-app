package com.example.cryptoapp.data.network.model

import com.google.gson.annotations.SerializedName

data class CoinNameDto(
    @SerializedName("Name")
    var name: String? = null,
    @SerializedName("FullName")
    var fullName: String? = null
)