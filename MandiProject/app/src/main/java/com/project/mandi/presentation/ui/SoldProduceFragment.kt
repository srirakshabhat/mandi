package com.project.mandi.presentation.ui

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.project.mandi.R
import com.project.mandi.databinding.SellProduceFragmentBinding
import com.project.mandi.databinding.SoldProduceFragmentBinding
import com.project.mandi.presentation.ui.viewmodel.SellProduceViewModel

class SoldProduceFragment : Fragment() {

    private lateinit var binding:SoldProduceFragmentBinding

    override fun onCreateView(inflater:LayoutInflater, container:ViewGroup?, savedInstanceState:Bundle?):View? {
        binding = SoldProduceFragmentBinding.inflate(inflater, container, false)
        populateSoldData()
        onSellMoreClick()
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    private fun populateSoldData() {
        arguments?.let {
            binding.ackText.text =  "Thank you "+ it.getString("seller_name") +" for selling your quality produce"
            binding.price.text =  "Please ensure you received "+ it.getString("total_price") +" for "+
                    it.getString("weight") + " Tonnes of your produce"
        }
    }

    /*on sell more produce clicked*/
    private fun onSellMoreClick() {
        binding.sellMore.setOnClickListener(View.OnClickListener {
            val navController = Navigation.findNavController(requireActivity(),R.id.fragment)
            navController.navigate(R.id.action_soldProduceFragment_to_sellProduceFragment)
        })
    }

}