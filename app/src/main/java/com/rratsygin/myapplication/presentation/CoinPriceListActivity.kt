package com.rratsygin.myapplication.presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ReportFragment.Companion.reportFragment
import androidx.lifecycle.ViewModelProvider
import com.rratsygin.myapplication.R
import com.rratsygin.myapplication.presentation.adapters.CoinInfoAdapter
import com.rratsygin.myapplication.databinding.ActivityCoinPriceListBinding
import com.rratsygin.myapplication.data.network.model.CoinInfoDto
import com.rratsygin.myapplication.domain.CoinInfo

class CoinPriceListActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityCoinPriceListBinding.inflate(layoutInflater)
    }


    private lateinit var viewModel: CoinViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val adapter = CoinInfoAdapter(this)
        binding.rvCoinPriceList.adapter = adapter
        binding.rvCoinPriceList.itemAnimator = null

        adapter.onCoinClickListener = object : CoinInfoAdapter.OnCoinClickListener {
            override fun onCoinClick(coinInfo: CoinInfo) {
                if (isOnePaneMode()) {
                    launchDetailActivity(coinInfo.fromSymbol)
                } else {
                    launchDetailFragment(coinInfo.fromSymbol)
                }


            }
        }

        viewModel = ViewModelProvider(this)[CoinViewModel::class.java]
        viewModel.coinInfoList.observe(this) {
            adapter.submitList(it)
        }
    }

    private fun launchDetailActivity(fromSymbol: String) {
        val intent = CoinDetailActivity.newIntent(
            this@CoinPriceListActivity,
            fromSymbol)
        startActivity(intent)
    }

    private fun isOnePaneMode() = binding.fragmentContainer == null

private fun launchDetailFragment(fromSymbol : String) {
    supportFragmentManager.popBackStack()
    supportFragmentManager
        .beginTransaction()
        .replace(R.id.fragment_container, CoinDetailFragment.newInstance(fromSymbol))
        .addToBackStack(null)
        .commit()
}
}