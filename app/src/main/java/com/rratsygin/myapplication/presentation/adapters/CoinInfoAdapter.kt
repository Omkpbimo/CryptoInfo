package com.rratsygin.myapplication.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rratsygin.myapplication.R
import com.rratsygin.myapplication.databinding.ItemCoinInfoBinding
import com.rratsygin.myapplication.data.network.model.CoinInfoDto
import com.squareup.picasso.Picasso

class CoinInfoAdapter(private val context: Context) : RecyclerView.Adapter<CoinInfoAdapter.CoinInfoViewHolder>() {



    var coinInfoList : List<CoinInfoDto> = listOf()
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
        val lastUpdate = context.resources.getString(R.string.label_update)


        with(binding) {

            tvSymbols.text = coin.fromSymbol + "/" + coin.toSymbol
            tvPrice.text = coin.price.toString()
//            tvLastUpdate.text = String.format(lastUpdate, coin.getFormattedTime())
            tvLastUpdate.text = lastUpdate + coin.getFormattedTime()
            Picasso.get().load(coin.getFullImageUrl()).into(tvLogo)
        }
        holder.itemView.setOnClickListener {
            onCoinClickListener?.onCoinClick(coin)
        }
    }

    override fun getItemCount(): Int {
        return coinInfoList.size
    }

    class CoinInfoViewHolder(val binding : ItemCoinInfoBinding) : RecyclerView.ViewHolder(binding.root) {


    }

    interface OnCoinClickListener {
        fun onCoinClick(coinInfoDto: CoinInfoDto)
    }

}