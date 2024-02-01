package com.example.cryptoapp.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.cryptoapp.R
import com.example.cryptoapp.data.network.ApiFactory
import com.example.cryptoapp.databinding.ItemCoinInfoBinding
import com.example.cryptoapp.domain.CoinInfo
import com.example.cryptoapp.utils.convertTimestampToTime
import com.squareup.picasso.Picasso

class CoinInfoAdapter(private val context: Context): Adapter<CoinInfoAdapter.CoinInfoViewHolder>() {

    inner class CoinInfoViewHolder(binding: ItemCoinInfoBinding): RecyclerView.ViewHolder(binding.root) {
        val imLogoCoin = binding.imLogoCoin
        val tvSymbols = binding.tvSymbols
        val tvPrice = binding.tvPrice
        val tvLastUpdate = binding.tvLastUpdate
    }

    var coinInfoList = listOf<CoinInfo>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var onCoinClickLister: (CoinInfo) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinInfoViewHolder {
        val binding = ItemCoinInfoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CoinInfoViewHolder(binding)
    }

    override fun getItemCount() = coinInfoList.size

    override fun onBindViewHolder(holder: CoinInfoViewHolder, position: Int) {
        val coin = coinInfoList[position]
        with(holder) {
//            tvSymbols.text = "${coin.fromSymbol} / ${coin.toSymbol}"
            tvSymbols.text = context.getString(R.string.symbols_template, coin.fromSymbol, coin.toSymbol)
            tvPrice.text = "%.4f".format(coin.price)
            tvLastUpdate.text = context.getString(R.string.last_update_template, convertTimestampToTime(coin.lastUpdate))

            Picasso.get()
                .load(ApiFactory.BASE_IMAGE_URL + coin.imageUrl)
                .into(imLogoCoin)

            itemView.setOnClickListener {
                onCoinClickLister(coin)
            }
        }
    }
}