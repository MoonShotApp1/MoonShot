package com.dartmouth.moonshot.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
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
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.*

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null
    private lateinit var buttonSignOut: Button
    private lateinit var buttonUpdateName: Button
    private lateinit var edittextName: EditText
    private lateinit var textviewName: TextView

    private var mFirebaseAuth: FirebaseAuth? = null
    private var firestoreDB = Firebase.firestore

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

        edittextName = root.findViewById(R.id.edittext_name)
        textviewName = root.findViewById(R.id.text_home)

        buttonSignOut = root.findViewById(R.id.button_signout)
        buttonSignOut.setOnClickListener(){
                signOutUser()
            }

        buttonUpdateName = root.findViewById(R.id.button_update_name)
        buttonUpdateName.setOnClickListener(){
            updateName()
        }

        return root
    }

    override fun onResume() {
        super.onResume()
        mFirebaseAuth = FirebaseAuth.getInstance()

        // Get saved name
        if(mFirebaseAuth != null) {
            if(mFirebaseAuth!!.currentUser != null) {
                val docRef =
                    firestoreDB.collection("users").document(mFirebaseAuth!!.currentUser!!.uid)
                docRef.get().addOnSuccessListener {
                    val newName = it["name"].toString()
                    textviewName.text = newName
                }
            }
        }
    }

    fun updateName(){
        val newName = edittextName.text
        // Create hash map with newName
        val doc = firestoreDB.collection("users").document(mFirebaseAuth!!.currentUser!!.uid)
        val updates = hashMapOf<String, Any>(
            "name" to newName.toString()
        )
        doc.set(updates)
        textviewName.text = newName
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