package com.example.cryptoapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import com.example.cryptoapp.R
import com.example.cryptoapp.presentation.adapters.CoinInfoAdapter
import com.example.cryptoapp.databinding.ActivityCoinPriceListBinding

class CoinPriceListActivity : FragmentActivity() {

    private lateinit var viewModel: CoinViewModel

    private val binding by lazy {
        ActivityCoinPriceListBinding.inflate(layoutInflater)
    }

    private val isLandscapeMode: Boolean
        get() = binding.fragmentContainerView != null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        val adapter = CoinInfoAdapter(this)
        binding.rvCoinPriceList.adapter = adapter
        binding.rvCoinPriceList.itemAnimator = null

        adapter.onCoinClickLister = {
            if (isLandscapeMode) {
                supportFragmentManager
                    .beginTransaction()
                    .replace(
                        R.id.fragment_container_view,
                        CoinDetailFragment.newInstance(it.fromSymbol)
                    )
                    .commit()
            } else {
                startActivity(CoinDetailActivity.newIntent(this, it.fromSymbol))
            }
        }

        viewModel = ViewModelProvider(this)[CoinViewModel::class.java]
        viewModel.coinInfoList.observe(this) {
            adapter.submitList(it)
        }
    }
}