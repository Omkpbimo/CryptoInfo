package com.rratsygin.myapplication.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rratsygin.myapplication.R
import com.rratsygin.myapplication.databinding.ItemCoinInfoBinding
import com.rratsygin.myapplication.domain.CoinInfo
import com.squareup.picasso.Picasso

class CoinInfoAdapter(private val context: Context) : RecyclerView.Adapter<CoinInfoViewHolder>() {



    var coinInfoList : List<CoinInfo> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var onCoinClickListener: OnCoinClickListener? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinInfoViewHolder {
        val binding = ItemCoinInfoBinding
            .inflate(
                LayoutInflater.from(parent.context),
                parent,
                false)
    return CoinInfoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CoinInfoViewHolder, position: Int) {
        val coin = coinInfoList[position]
        val binding = holder.binding
        val lastUpdateTime = context.resources.getString(R.string.label_update)


        with(binding) {

            tvSymbols.text = coin.fromSymbol + "/" + coin.toSymbol
            tvPrice.text = coin.price
//            tvLastUpdate.text = String.format(lastUpdateTime, convertTimestampToTime(coin.lastUpdate))
            tvLastUpdate.text = lastUpdateTime + coin.lastUpdate
            Picasso.get().load(coin.imageurl).into(tvLogo)
        }
        binding.root.setOnClickListener {
            onCoinClickListener?.onCoinClick(coin)
        }
    }

    override fun getItemCount(): Int {
        return coinInfoList.size
    }


    interface OnCoinClickListener {
        fun onCoinClick(coinPriceInfo: CoinInfo)
    }

}