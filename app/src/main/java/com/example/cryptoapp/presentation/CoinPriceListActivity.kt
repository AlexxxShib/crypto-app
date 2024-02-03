package com.example.cryptoapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModelProvider
import com.example.cryptoapp.presentation.adapters.CoinInfoAdapter
import com.example.cryptoapp.databinding.ActivityCoinPriceListBinding

class CoinPriceListActivity : ComponentActivity() {

    private lateinit var viewModel: CoinViewModel

    private val binding by lazy {
        ActivityCoinPriceListBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        val adapter = CoinInfoAdapter(this)
        binding.rvCoinPriceList.adapter = adapter
        binding.rvCoinPriceList.itemAnimator = null

        adapter.onCoinClickLister = {
            startActivity(CoinDetailActivity.newIntent(this, it.fromSymbol))
        }

        viewModel = ViewModelProvider(this)[CoinViewModel::class.java]
        viewModel.coinInfoList.observe(this) {
            adapter.submitList(it)
        }
    }
}