package com.dartmouth.moonshot

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class CoinListAdapter(val context: Activity, var coinList: ArrayList<Coin>): RecyclerView.Adapter<CoinListAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewName: TextView
        val textViewSymb: TextView
        val textViewBCType: TextView
        val textViewCurrPrice: TextView
        val iconPic: ImageView

        init {
            // Define click listener for the ViewHolder's View.
            textViewName = view.findViewById(R.id.tv_name)
            textViewSymb = view.findViewById(R.id.tv_symbol)
            textViewBCType = view.findViewById(R.id.tv_blockchaintype)
            textViewCurrPrice = view.findViewById(R.id.tv_currentprice)
            iconPic = view.findViewById(R.id.iconPic)
        }
    }
    /*override fun getCount(): Int {
        return coinList.size
    }

    override fun getItem(p0: Int): Any {
        return coinList.get(p0)
    }*/

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    /*override fun getView(position: Int, p1: View?, p2: ViewGroup?): View {
        val view: View = View.inflate(context, R.layout.layout_reccoin_list,null)

        val textViewName = view.findViewById(R.id.tv_name) as TextView
        val textViewSymb = view.findViewById(R.id.tv_symbol) as TextView
        val textViewBCType = view.findViewById(R.id.tv_blockchaintype) as TextView
        val textViewCurrPrice = view.findViewById(R.id.tv_currentprice) as TextView
        val iconPic = view.findViewById(R.id.iconPic) as ImageView

        textViewName.text = "NAME: " + coinList.get(position).name
        textViewSymb.text = "SYMBOL: " + coinList.get(position).symbol
        textViewBCType.text = "PLATFORM: " + coinList.get(position).platforms
        textViewCurrPrice.text = "PRICE $" + coinList.get(position).currentPrice.toString()
        Picasso.get().load(coinList.get(position).image_large).into(iconPic)

        return view
    }*/

    fun replace(newEntryList: ArrayList<Coin>){
        coinList = newEntryList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.layout_reccoin_list, parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textViewName.text = "NAME: " + coinList.get(position).name
        holder.textViewSymb.text = "SYMBOL: " + coinList.get(position).symbol
        holder.textViewBCType.text = "PLATFORM: " + coinList.get(position).platforms
        holder.textViewCurrPrice.text = "PRICE $" + coinList.get(position).currentPrice.toString()
        Picasso.get().load(coinList.get(position).image_large).into(holder.iconPic)
    }

    override fun getItemCount(): Int {
        return coinList.size
    }

}