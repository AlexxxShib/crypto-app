package com.example.cryptoapp.presentation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import com.example.cryptoapp.CryptoApp
import com.example.cryptoapp.R
import com.example.cryptoapp.databinding.ActivityCoinPriceListBinding
import com.example.cryptoapp.presentation.adapters.CoinInfoAdapter
import javax.inject.Inject

class CoinPriceListActivity : FragmentActivity() {

    private val component by lazy {
        (application as CryptoApp).component
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[CoinViewModel::class.java]
    }

    private val binding by lazy {
        ActivityCoinPriceListBinding.inflate(layoutInflater)
    }

    private val isLandscapeMode: Boolean
        get() = binding.fragmentContainerView != null

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
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
        Log.d("CoinPriceListActivity", "Activity ViewModel $viewModel")
        viewModel.coinInfoList.observe(this) {
            adapter.submitList(it)
        }
    }
}