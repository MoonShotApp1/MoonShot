package com.dartmouth.moonshot.ui.gallery

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.dartmouth.moonshot.IndividualCoinActivity
import com.dartmouth.moonshot.R
import com.dartmouth.moonshot.databinding.FragmentGalleryBinding

class GalleryFragment : Fragment() {

    private lateinit var galleryViewModel: GalleryViewModel


    // This property is only valid between onCreateView and
    // onDestroyView.


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val ret = inflater.inflate(R.layout.fragment_gallery, container, false)

        val listView = ret.findViewById<ListView>(R.id.listview_savedcoins)

        val coinList: ArrayList<String> = arrayListOf("Bitcoin", "Litecoin", "Ethereum", "Dogecoin")

        val adapter = ArrayAdapter(requireActivity(), android.R.layout.simple_list_item_1, coinList)
        listView.adapter = adapter

        listView.setOnItemClickListener() { _, _, position, _ ->
            val coin = coinList[position]

            val intent = Intent(requireActivity(), IndividualCoinActivity::class.java)
            val bundle = Bundle()
            val address = ""
            val blockchainType = ""
            val currentPrice = ""
            val dailyVolume = ""
            val holders = ""
            val name = ""
            val symbol = ""
            bundle.putString(IndividualCoinActivity.ADDRESS_KEY, address)
            bundle.putString(IndividualCoinActivity.BLOCKCHAIN_TYPE_KEY, blockchainType)
            bundle.putString(IndividualCoinActivity.CURRENT_PRICE_KEY, currentPrice)
            bundle.putString(IndividualCoinActivity.DAILY_VOLUME_KEY, dailyVolume)
            bundle.putString(IndividualCoinActivity.HOLDERS_KEY, holders)
            bundle.putString(IndividualCoinActivity.NAME_KEY, name)
            bundle.putString(IndividualCoinActivity.SYMBOL_KEY, symbol)
            intent.putExtra(IndividualCoinActivity.BUNDLE_KEY, bundle)
            requireActivity().startActivity(intent)
        }

        return ret
    }

    override fun onDestroyView() {
        super.onDestroyView()

    }
}