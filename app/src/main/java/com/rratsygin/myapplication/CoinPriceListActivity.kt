package com.rratsygin.myapplication

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.rratsygin.myapplication.adapter.CoinInfoAdapter
import com.rratsygin.myapplication.databinding.ActivityCoinPriceListBinding
import com.rratsygin.myapplication.pojo.CoinPriceInfo

class CoinPriceListActivity : AppCompatActivity() {

    private lateinit var _binding : ActivityCoinPriceListBinding



    private lateinit var viewModel : CoinViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityCoinPriceListBinding.inflate(layoutInflater)
        setContentView(_binding.root)
        val adapter = CoinInfoAdapter(this)
        _binding.rvCoinPriceList.adapter = adapter

        adapter.onCoinClickListener = object : CoinInfoAdapter.OnCoinClickListener {
            override fun onCoinClick(coinPriceInfo: CoinPriceInfo) {
                val intent = CoinDetailActivity.newIntent(this@CoinPriceListActivity, coinPriceInfo.fromSymbol)
                Log.d("ON_CLICK_TEST", coinPriceInfo.fromSymbol)
                startActivity(intent)
            }
        }

        viewModel = ViewModelProvider(this)[CoinViewModel::class.java]
        viewModel.priceList.observe(this, Observer {
            adapter.coinInfoList = it
        })


//        viewModel.getDetailInfo("BTC").observe(this, Observer {
//            Log.d("TEST_OF_LOADING_DATA", "From activity $it")
//        })


        }





}