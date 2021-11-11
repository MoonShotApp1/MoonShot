package com.dartmouth.moonshot

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class CoinListAdapter(val context: Activity, var coinList: ArrayList<Coin>): BaseAdapter()  {
    override fun getCount(): Int {
        return coinList.size
    }

    override fun getItem(p0: Int): Any {
        return coinList.get(p0)
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(position: Int, p1: View?, p2: ViewGroup?): View {
        val view: View = View.inflate(context, R.layout.layout_reccoin_list,null)

        val textViewName = view.findViewById(R.id.tv_name) as TextView
        val textViewSymb = view.findViewById(R.id.tv_symbol) as TextView
        val textViewBCType = view.findViewById(R.id.tv_blockchaintype) as TextView
        val textViewCurrPrice = view.findViewById(R.id.tv_currentprice) as TextView

        textViewName.text = coinList.get(position).name
        textViewSymb.text = coinList.get(position).symbol
        textViewBCType.text = coinList.get(position).platforms
        textViewCurrPrice.text = coinList.get(position).currentPrice

        return view
    }

    fun replace(newEntryList: ArrayList<Coin>){
        coinList = newEntryList
    }
}