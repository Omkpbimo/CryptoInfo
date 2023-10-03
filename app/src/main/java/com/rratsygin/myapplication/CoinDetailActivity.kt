package com.rratsygin.myapplication

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.rratsygin.myapplication.databinding.ActivityCoinDetailBinding
import com.squareup.picasso.Picasso

class CoinDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCoinDetailBinding

    private lateinit var viewModel : CoinViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoinDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fromSymbol = intent.getStringExtra(EXTRA_FROM_SYMBOL)
        viewModel = ViewModelProvider(this)[CoinViewModel::class.java]
        if (fromSymbol != null) {
            viewModel.getDetailInfo(fromSymbol).observe(this, Observer {
                with(binding) {
                    tvPrice.text = it.price
                    tvMinPrice.text = it.lowday
                    tvMaxPrice.text = it.highday
                    tvLastMarket.text = it.lastMarket
                    tvLastUpdate.text  = it.getFormattedTime()
                    tvFromSymbol.text = it.fromSymbol
                    tvToSymbol.text = it.toSymbol
                    Picasso.get().load(it.getFullImageUrl()).into(ivLogoCoin)
                }
            })
        }

    }

    companion object {
        const val EXTRA_FROM_SYMBOL = "fSym"

        fun newIntent(context : Context, fromSymbol : String) : Intent {
            val intent = Intent(context, CoinDetailActivity::class.java)
            intent.putExtra(EXTRA_FROM_SYMBOL, fromSymbol)
            return intent
        }

    }

}