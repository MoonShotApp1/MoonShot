package com.dartmouth.moonshot.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.dartmouth.moonshot.LogInActivity
import com.dartmouth.moonshot.R
import com.dartmouth.moonshot.databinding.FragmentHomeBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import java.util.*

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null
    private lateinit var buttonSignOut: Button

    private var mFirebaseAuth: FirebaseAuth? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        mFirebaseAuth = FirebaseAuth.getInstance()

        buttonSignOut = root.findViewById(R.id.button_signout)
        buttonSignOut.setOnClickListener(){
                signOutUser()
            }

        return root
    }

    fun signOutUser(){
        mFirebaseAuth!!.signOut()
        logIn()
    }

    fun logIn(){
        val intent = Intent(requireActivity(), LogInActivity::class.java)
        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}