package com.example.cryptoapp.pojo

import com.google.gson.annotations.SerializedName

data class CoinInfoListOfData (
    @SerializedName("Data") var data: ArrayList<Data> = arrayListOf(),
)