package com.rratsygin.myapplication.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.rratsygin.myapplication.databinding.ActivityCoinDetailBinding
import com.rratsygin.myapplication.databinding.FragmentCoinDetailBinding
import com.squareup.picasso.Picasso
import java.lang.RuntimeException

class CoinDetailFragment : Fragment() {

    private var _binding : FragmentCoinDetailBinding? = null
    private val binding : FragmentCoinDetailBinding
        get() = _binding?: throw RuntimeException("Coin detail is null")

    private lateinit var viewModel : CoinViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCoinDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fromSymbol = getSymbol()
        viewModel = ViewModelProvider(this)[CoinViewModel::class.java]
        viewModel.getDetailInfo(fromSymbol).observe(viewLifecycleOwner){
            with(binding) {
                tvPrice.text = it.price
                tvMinPrice.text = it.lowday
                tvMaxPrice.text = it.highday
                tvLastMarket.text = it.lastMarket
                tvLastUpdate.text  = it.lastUpdate
                tvFromSymbol.text = it.fromSymbol
                tvToSymbol.text = it.toSymbol
                Picasso.get().load(it.imageurl).into(ivLogoCoin)
            }
    }




    }private fun getSymbol() : String {
        return requireArguments().getString(EXTRA_FROM_SYMBOL, "")
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    companion object {
        private const val EXTRA_FROM_SYMBOL = "fSym"
        private const val EMPTY_SYMBOL = ""

        fun newInstance(fromSymbol : String) : Fragment {
           return CoinDetailFragment().apply {
               arguments = Bundle().apply {
                   putString(EXTRA_FROM_SYMBOL, fromSymbol)
               }
           }
        }




    }

}