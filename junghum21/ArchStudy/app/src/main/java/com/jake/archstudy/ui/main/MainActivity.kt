package com.jake.archstudy.ui.main

import android.os.Bundle
import androidx.databinding.Observable
import androidx.fragment.app.FragmentStatePagerAdapter
import com.jake.archstudy.R
import com.jake.archstudy.base.BaseActivity
import com.jake.archstudy.data.model.Market
import com.jake.archstudy.data.source.UpbitRemoteDataSource
import com.jake.archstudy.data.source.UpbitRepository
import com.jake.archstudy.databinding.ActivityMainBinding
import com.jake.archstudy.network.ApiUtil
import com.jake.archstudy.ui.tickers.TickersFragment

class MainActivity :
    BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val mainViewModel = MainViewModel(
        UpbitRepository.getInstance(UpbitRemoteDataSource(ApiUtil.getUpbitService()))
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initTabLayout()
        observeMarkets()
        mainViewModel.getMarketAll()
    }

    private fun observeMarkets() {
        mainViewModel.markets.run {
            addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
                override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                    setViewPager(get() ?: return)
                }
            })
        }
    }

    private fun setViewPager(markets: List<Market>) {
        binding.vpContent.adapter = object : FragmentStatePagerAdapter(supportFragmentManager) {
            override fun getItem(position: Int) =
                TickersFragment.newInstance(markets[position].markets)

            override fun getCount() = markets.size

            override fun getPageTitle(position: Int) = markets[position].title
        }
    }

    private fun initTabLayout() {
        binding.tlMarket.setupWithViewPager(binding.vpContent)
    }

}