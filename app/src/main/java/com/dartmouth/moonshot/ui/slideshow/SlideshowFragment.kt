package com.dartmouth.moonshot.ui.slideshow

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.dartmouth.moonshot.*
import com.dartmouth.moonshot.databinding.FragmentSlideshowBinding

class SlideshowFragment : Fragment() {

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

        coinListView = binding.listv
        arrayList = ArrayList()
        arrayAdapter = CoinListAdapter(requireActivity(), arrayList)
        coinListView.adapter = arrayAdapter

        coinListView.setOnItemClickListener() { parent: AdapterView<*>, view: View, position: Int, id: Long ->
            val itemSelected: Coin = parent.getItemAtPosition(position) as Coin

            val indCoinIntent = Intent(requireActivity(), IndividualCoinActivity::class.java)

            indCoinIntent.putExtra("name", itemSelected.name)
            indCoinIntent.putExtra("symbol", itemSelected.symbol)
            indCoinIntent.putExtra("BCType", itemSelected.blockchainType)
            indCoinIntent.putExtra("currPrice", itemSelected.currentPrice)

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