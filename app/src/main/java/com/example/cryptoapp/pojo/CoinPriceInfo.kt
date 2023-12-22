package com.example.cryptoapp.pojo

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.cryptoapp.api.ApiFactory
import com.example.cryptoapp.utils.convertTimestampToTime
import com.google.gson.annotations.SerializedName
import io.reactivex.rxjava3.annotations.NonNull

@Entity(tableName = "full_price_list")
data class CoinPriceInfo (
    @SerializedName("TYPE"                    ) var type                    : String? = null,
    @SerializedName("MARKET"                  ) var market                  : String? = null,

    @PrimaryKey
    @SerializedName("FROMSYMBOL"              ) var fromSymbol              : String = "",

    @SerializedName("TOSYMBOL"                ) var toSymbol                : String? = null,
    @SerializedName("FLAGS"                   ) var flags                   : String? = null,
    @SerializedName("LASTMARKET"              ) var lastMarket              : String? = null,
    @SerializedName("MEDIAN"                  ) var median                  : Double? = null,
    @SerializedName("TOPTIERVOLUME24HOUR"     ) var topTierVolume24Hour     : Double? = null,
    @SerializedName("TOPTIERVOLUME24HOURTO"   ) var topTierVolume24HourTo   : Double? = null,
    @SerializedName("LASTTRADEID"             ) var lastTradeid             : String? = null,
    @SerializedName("PRICE"                   ) var price                   : Double? = null,
    @SerializedName("LASTUPDATE"              ) var lastUpdate              : Long?   = null,
    @SerializedName("LASTVOLUME"              ) var lastVolume              : Double? = null,
    @SerializedName("LASTVOLUMETO"            ) var lastVolumeTo            : Double? = null,
    @SerializedName("VOLUMEHOUR"              ) var volumeHour              : Double? = null,
    @SerializedName("VOLUMEHOURTO"            ) var volumeHourTo            : Double? = null,
    @SerializedName("OPENHOUR"                ) var openHour                : Double? = null,
    @SerializedName("HIGHHOUR"                ) var highHour                : Double? = null,
    @SerializedName("LOWHOUR"                 ) var lowHour                 : Double? = null,
    @SerializedName("VOLUMEDAY"               ) var volumeDay               : Double? = null,
    @SerializedName("VOLUMEDAYTO"             ) var volumeDayTo             : Double? = null,
    @SerializedName("OPENDAY"                 ) var openDay                 : Double? = null,
    @SerializedName("HIGHDAY"                 ) var highDay                 : Double? = null,
    @SerializedName("LOWDAY"                  ) var lowDay                  : Double? = null,
    @SerializedName("VOLUME24HOUR"            ) var volume24Hour            : Double? = null,
    @SerializedName("VOLUME24HOURTO"          ) var volume24HourTo          : Double? = null,
    @SerializedName("OPEN24HOUR"              ) var open24Hour              : Double? = null,
    @SerializedName("HIGH24HOUR"              ) var high24Hour              : Double? = null,
    @SerializedName("LOW24HOUR"               ) var low24Hour               : Double? = null,
    @SerializedName("CHANGE24HOUR"            ) var change24Hour            : Double? = null,
    @SerializedName("CHANGEPCT24HOUR"         ) var changepct24Hour         : Double? = null,
    @SerializedName("CHANGEDAY"               ) var changeDay               : Double? = null,
    @SerializedName("CHANGEPCTDAY"            ) var changepctDay            : Double? = null,
    @SerializedName("CHANGEHOUR"              ) var changeHour              : Double? = null,
    @SerializedName("CHANGEPCTHOUR"           ) var changepctHour           : Double? = null,
    @SerializedName("CONVERSIONTYPE"          ) var conversionType          : String? = null,
    @SerializedName("CONVERSIONSYMBOL"        ) var conversionSymbol        : String? = null,
    @SerializedName("CONVERSIONLASTUPDATE"    ) var conversionLastUpdate    : Int?    = null,
    @SerializedName("SUPPLY"                  ) var supply                  : Int?    = null,
    @SerializedName("MKTCAP"                  ) var mktCap                  : Double? = null,
    @SerializedName("MKTCAPPENALTY"           ) var mktCapPenalty           : Int?    = null,
    @SerializedName("CIRCULATINGSUPPLY"       ) var circulatingSupply       : Int?    = null,
    @SerializedName("CIRCULATINGSUPPLYMKTCAP" ) var circulatingSupplyMktCap : Double? = null,
    @SerializedName("TOTALVOLUME24H"          ) var totalVolume24H          : Double? = null,
    @SerializedName("TOTALVOLUME24HTO"        ) var totalVolume24HTo        : Double? = null,
    @SerializedName("TOTALTOPTIERVOLUME24H"   ) var totalTopTierVolume24H   : Double? = null,
    @SerializedName("TOTALTOPTIERVOLUME24HTO" ) var totalTopTierVolume24HTo : Double? = null,
    @SerializedName("IMAGEURL"                ) var imageUrl                : String? = null
) {
    fun getFormattedLastUpdateTime(): String {
        return convertTimestampToTime(lastUpdate)
    }

    fun getFullImageUrl(): String {
        return ApiFactory.BASE_IAMGE_URL + imageUrl
    }
}