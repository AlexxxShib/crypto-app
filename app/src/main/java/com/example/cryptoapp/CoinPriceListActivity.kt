package com.example.cryptoapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModelProvider
import com.example.cryptoapp.adapters.CoinInfoAdapter
import com.example.cryptoapp.databinding.ActivityCoinPriceListBinding
import com.example.cryptoapp.pojo.CoinPriceInfo

class CoinPriceListActivity : ComponentActivity() {

    private lateinit var binding: ActivityCoinPriceListBinding
    private lateinit var viewModel: CoinViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCoinPriceListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = CoinInfoAdapter(this)
        binding.rvCoinPriceList.adapter = adapter

        /*adapter.onCoinClickLister = object : CoinInfoAdapter.OnCoinClickLister {
            override fun onCoinClick(coinPriceInfo: CoinPriceInfo) {
                Log.d("CLICK", "Click by ${coinPriceInfo.fromSymbol}")
            }
        }*/

        adapter.onCoinClickLister = {
            startActivity(CoinDetailActivity.newIntent(this, it.fromSymbol))
        }

        viewModel = ViewModelProvider(this)[CoinViewModel::class.java]
        viewModel.getPriceList().observe(this) {
//            Log.d("TEST_LOADING_DATA", "In Activity: $it")
            adapter.coinInfoList = it
        }
//        viewModel.getDetailInfo("BTC").observe(this) {
//            Log.d("TEST_LOADING_DATA", "In Activity: $it")
//        }
    }
}