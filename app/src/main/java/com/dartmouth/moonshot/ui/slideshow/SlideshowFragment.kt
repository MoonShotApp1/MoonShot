package com.dartmouth.moonshot.ui.slideshow

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.dartmouth.moonshot.*
import com.dartmouth.moonshot.databinding.FragmentSlideshowBinding

class SlideshowFragment : Fragment() {

    lateinit var cryptosA: TextView
    lateinit var cryptosB: TextView
    lateinit var exchangesA: TextView
    lateinit var exchangesB: TextView
    lateinit var marketCapA: TextView
    lateinit var marketCapB: TextView

    lateinit var radgrp: RadioGroup
    lateinit var liked: Button
    lateinit var all: Button
    lateinit var eth: Button
    lateinit var bnb: Button
    lateinit var other1: Button
    lateinit var other2: Button

    lateinit var name: TextView
    lateinit var price: TextView
    lateinit var tfhp: TextView
    lateinit var mrktCap: TextView


    private lateinit var slideshowViewModel: SlideshowViewModel
    private var _binding: FragmentSlideshowBinding? = null


    private lateinit var coinListView: ListView
    private lateinit var arrayList: ArrayList<Coin>
    private lateinit var arrayAdapter: CoinListAdapter

    private lateinit var coinViewModel: CoinViewModel

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        slideshowViewModel =
            ViewModelProvider(this).get(SlideshowViewModel::class.java)

        _binding = FragmentSlideshowBinding.inflate(inflater, container, false)
        val root: View = binding.root

        //------------------------------------------------------------------------------------
        cryptosA = root.findViewById(R.id.cryptosA)
        cryptosB = root.findViewById(R.id.cryptosB)
        exchangesA = root.findViewById(R.id.exchangesA)
        exchangesB = root.findViewById(R.id.exchangesB)
        marketCapA = root.findViewById(R.id.marketCapA)
        marketCapB = root.findViewById(R.id.marketCapB)

        radgrp = root.findViewById(R.id.radgrp)
        liked = root.findViewById(R.id.liked)
        all = root.findViewById(R.id.all)
        eth = root.findViewById(R.id.eth)
        bnb = root.findViewById(R.id.bnb)
        other1 = root.findViewById(R.id.other1)
        other2 = root.findViewById(R.id.other2)

        name = root.findViewById(R.id.name)
        price = root.findViewById(R.id.price)
        tfhp = root.findViewById(R.id.tfhp)
        mrktCap = root.findViewById(R.id.mrktCap)

        coinListView = binding.listv

        //------------------------------------------------------------------------------------

        //xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
        radgrp.setOnCheckedChangeListener { radioGroup, optionId ->
            run {
                when (optionId) {
                    R.id.liked -> {
                        liked.rotation = 30F
                        all.rotation = 0F
                        eth.rotation = 0F
                        bnb.rotation = 0F
                        other1.rotation = 0F
                        other2.rotation = 0F

                        liked.setTextColor(Color.parseColor("#FFFF00"))
                        all.setTextColor(Color.parseColor("#FFFFFF"))
                        eth.setTextColor(Color.parseColor("#FFFFFF"))
                        bnb.setTextColor(Color.parseColor("#FFFFFF"))
                        other1.setTextColor(Color.parseColor("#FFFFFF"))
                        other2.setTextColor(Color.parseColor("#FFFFFF"))
                    }

                    R.id.all -> {
                        liked.rotation = 0F
                        all.rotation = 30F
                        eth.rotation = 0F
                        bnb.rotation = 0F
                        other1.rotation = 0F
                        other2.rotation = 0F

                        liked.setTextColor(Color.parseColor("#FFFFFF"))
                        all.setTextColor(Color.parseColor("#FFFF00"))
                        eth.setTextColor(Color.parseColor("#FFFFFF"))
                        bnb.setTextColor(Color.parseColor("#FFFFFF"))
                        other1.setTextColor(Color.parseColor("#FFFFFF"))
                        other2.setTextColor(Color.parseColor("#FFFFFF"))
                    }
                    R.id.eth -> {
                        liked.rotation = 0F
                        all.rotation = 0F
                        eth.rotation = 30F
                        bnb.rotation = 0F
                        other1.rotation = 0F
                        other2.rotation = 0F

                        liked.setTextColor(Color.parseColor("#FFFFFF"))
                        all.setTextColor(Color.parseColor("#FFFFFF"))
                        eth.setTextColor(Color.parseColor("#FFFF00"))
                        bnb.setTextColor(Color.parseColor("#FFFFFF"))
                        other1.setTextColor(Color.parseColor("#FFFFFF"))
                        other2.setTextColor(Color.parseColor("#FFFFFF"))
                    }
                    R.id.bnb -> {
                        liked.rotation = 0F
                        all.rotation = 0F
                        eth.rotation = 0F
                        bnb.rotation = 30F
                        other1.rotation = 0F
                        other2.rotation = 0F

                        liked.setTextColor(Color.parseColor("#FFFFFF"))
                        all.setTextColor(Color.parseColor("#FFFFFF"))
                        eth.setTextColor(Color.parseColor("#FFFFFF"))
                        bnb.setTextColor(Color.parseColor("#FFFF00"))
                        other1.setTextColor(Color.parseColor("#FFFFFF"))
                        other2.setTextColor(Color.parseColor("#FFFFFF"))
                    }
                    R.id.other1 -> {
                        liked.rotation = 0F
                        all.rotation = 0F
                        eth.rotation = 0F
                        bnb.rotation = 0F
                        other1.rotation = 30F
                        other2.rotation = 0F

                        liked.setTextColor(Color.parseColor("#FFFFFF"))
                        all.setTextColor(Color.parseColor("#FFFFFF"))
                        eth.setTextColor(Color.parseColor("#FFFFFF"))
                        bnb.setTextColor(Color.parseColor("#FFFFFF"))
                        other1.setTextColor(Color.parseColor("#FFFF00"))
                        other2.setTextColor(Color.parseColor("#FFFFFF"))
                    }
                    R.id.other2 -> {var a = 0
                        liked.rotation = 0F
                        all.rotation = 0F
                        eth.rotation = 0F
                        bnb.rotation = 0F
                        other1.rotation = 0F
                        other2.rotation = 30F

                        liked.setTextColor(Color.parseColor("#FFFFFF"))
                        all.setTextColor(Color.parseColor("#FFFFFF"))
                        eth.setTextColor(Color.parseColor("#FFFFFF"))
                        bnb.setTextColor(Color.parseColor("#FFFFFF"))
                        other1.setTextColor(Color.parseColor("#FFFFFF"))
                        other2.setTextColor(Color.parseColor("#FFFF00"))
                    }
                }
            }
        }
        //xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx

        arrayList = ArrayList()
        arrayAdapter = CoinListAdapter(requireActivity(), arrayList)
        coinListView.adapter = arrayAdapter

        coinListView.setOnItemClickListener() { parent: AdapterView<*>, view: View, position: Int, id: Long ->
            val itemSelected: Coin = parent.getItemAtPosition(position) as Coin

            val indCoinIntent = Intent(requireActivity(), IndividualCoinActivity::class.java)

            indCoinIntent.putExtra(IndividualCoinActivity.NAME_KEY, itemSelected.name)
            indCoinIntent.putExtra(IndividualCoinActivity.SYMBOL_KEY, itemSelected.symbol)
            indCoinIntent.putExtra(IndividualCoinActivity.BLOCKCHAIN_TYPE_KEY, itemSelected.platforms)
            indCoinIntent.putExtra(IndividualCoinActivity.CURRENT_PRICE_KEY, itemSelected.currentPrice.toString())
            indCoinIntent.putExtra(IndividualCoinActivity.ADDRESS_KEY, itemSelected.address)
            indCoinIntent.putExtra(IndividualCoinActivity.IMAGE_LARGE_KEY, itemSelected.image_large)
            indCoinIntent.putExtra(IndividualCoinActivity.ID_KEY, itemSelected.id)
            //Toast.makeText(this.activity, itemSelected.name, Toast.LENGTH_LONG).show()
            //Toast.makeText(this.activity, itemSelected.image_large.toString(), Toast.LENGTH_LONG).show()

            startActivity(indCoinIntent)
        }

        coinViewModel =
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
                .create(CoinViewModel::class.java)

        coinViewModel.getAllCoins().observe(viewLifecycleOwner, Observer {
            arrayAdapter.replace(it as ArrayList<Coin>)
            arrayAdapter.notifyDataSetChanged()
        })

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}