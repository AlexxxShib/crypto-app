package com.example.cryptoapp.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.cryptoapp.R
import com.example.cryptoapp.databinding.ItemCoinInfoBinding
import com.example.cryptoapp.domain.CoinInfo
import com.squareup.picasso.Picasso

class CoinInfoAdapter(
    private val context: Context
) : ListAdapter<CoinInfo, CoinInfoViewHolder>(CoinInfoDiffCallback) {

    var onCoinClickLister: (CoinInfo) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinInfoViewHolder {
        val binding = ItemCoinInfoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CoinInfoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CoinInfoViewHolder, position: Int) {
        val coin = getItem(position)
        with(holder.binding) {
//            tvSymbols.text = "${coin.fromSymbol} / ${coin.toSymbol}"
            tvSymbols.text =
                context.getString(R.string.symbols_template, coin.fromSymbol, coin.toSymbol)
            tvPrice.text = "%.4f".format(coin.price)
            tvLastUpdate.text = context.getString(R.string.last_update_template, coin.lastUpdate)

            Picasso.get()
                .load(coin.imageUrl)
                .into(imLogoCoin)

            root.setOnClickListener {
                onCoinClickLister(coin)
            }
        }
    }

}