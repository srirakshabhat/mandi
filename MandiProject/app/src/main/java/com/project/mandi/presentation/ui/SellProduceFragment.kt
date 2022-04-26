package com.project.mandi.presentation.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.project.mandi.R
import com.project.mandi.data.model.SellProduce
import com.project.mandi.data.model.VillageData
import com.project.mandi.databinding.SellProduceFragmentBinding
import com.project.mandi.presentation.ui.viewmodel.SellProduceViewModel
import java.math.RoundingMode
import java.text.DecimalFormat


class SellProduceFragment : Fragment(), View.OnKeyListener{

    private var mSellerNameEntered:Boolean = false
    private lateinit var binding: SellProduceFragmentBinding
    private lateinit var viewModel: SellProduceViewModel

    /*inflate the view*/
    override fun onCreateView(inflater:LayoutInflater, container:ViewGroup?, savedInstanceState:Bundle?):View? {
        init(inflater, container)
        onSellProduceClick()
        populateData()
        onSellerNameNextClick()
        onVillageSelected()
        textWatcherForSellerName();
        textWatcherForLoyaltyCard();
        return binding.root
    }


    /*init layout and vm*/
    private fun init(inflater:LayoutInflater, container:ViewGroup?) {
        binding = SellProduceFragmentBinding.inflate(inflater, container, false)
        viewModel = (activity as MainActivity).viewModel
    }

    /*to populate required data for the screen*/
    private fun populateData() {
        populateVillages();
        saveSeller()
        getSavedDataObserver()
    }

    /*to get saved data*/
    private fun getSavedDataObserver() {
        viewModel.getMutableLiveData.observe(viewLifecycleOwner, Observer {
            if(it != null) {
                if(mSellerNameEntered) {
                    onSellerNameEntered(it)
                }
                else {
                    onLoyaltyEntered(it)
                }
                viewModel.getMutableLiveData.value = null
            }
        })
    }

    /*popultae seller name on loyalty entered*/
    private fun onLoyaltyEntered(it:List<SellProduce>) {
        var loyaltyCardFound = false
        it.forEach {
            if(binding.loyaltyCardId.text.toString().trim().equals(it.loyalty_card_id, ignoreCase = true)) {
                loyaltyCardFound = true
                binding.sellerName.setText(it.sellerName)
                binding.sellerName.isEnabled = false
                return@forEach
            }
        }
        if(!loyaltyCardFound) {
            binding.sellerName.setText("")
            Toast.makeText(requireContext(), "Loyalty card id doesn't exist!", Toast.LENGTH_SHORT).show();
            binding.sellerName.isEnabled = true
            setAppliedIndexData(getString(R.string.applied_index_low))
        }
        else {
            setAppliedIndexData(getString(R.string.applied_index_high))
        }
    }

    /*popultae loyalty on seller name entered*/
    private fun onSellerNameEntered(it:List<SellProduce>) {
        var sellerNameEntered = false
        it.forEach {
            if(binding.sellerName.text.toString().trim().equals(it.sellerName, ignoreCase = true)) {
                binding.loyaltyCardId.setText(it.loyalty_card_id)
                binding.loyaltyCardId.isEnabled = false
                sellerNameEntered = true
                return@forEach
            }
        }
        if(!sellerNameEntered) {
            binding.loyaltyCardId.setText("")
            binding.loyaltyCardId.isEnabled = true
            setAppliedIndexData(getString(R.string.applied_index_low))
        }
        else {
            setAppliedIndexData(getString(R.string.applied_index_high))
        }
    }

    private fun setAppliedIndexData(text:String) {
        binding.appliedLoyaltyIndex.text = text
    }

    /*to save dummy data into the database*/
    private fun saveSeller(){
        for (i in 1..10) {
            val sellProduce = SellProduce("Raamu kaka"+i, "S000"+i)
            viewModel.saveSeller(sellProduce)
        }
    }

    /*populate village data with price*/
    private fun populateVillages() {
        val list = ArrayList<VillageData>()
        list.add(VillageData("Ramangar",200.10))
        list.add(VillageData("Kanakpura",100.20))
        list.add(VillageData("Bangalore",120.30))
        list.add(VillageData("Hassan",150.40))
        binding.spinnerVillage.adapter = ArrayAdapter<VillageData>(requireContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, list)
    }

    /*next click listener for seller name, card id and weight*/
    private fun onSellerNameNextClick() {
        binding.sellerName.setOnKeyListener(this)
        binding.loyaltyCardId.setOnKeyListener(this)
        binding.weight.setOnKeyListener(this)
    }

    /*after entering data if user wants to change the vilage*/
    private fun onVillageSelected() {
        binding.spinnerVillage.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent:AdapterView<*>, view:View?, position:Int, id:Long) {
                if(isMandatoryFieldsFilled()){
                    calculateTotalPrice()
                }
            }
            override fun onNothingSelected(parent:AdapterView<*>?) {}
        })
    }

    /*on sell produce clicked*/
    private fun onSellProduceClick() {
        binding.sellProduce.setOnClickListener(View.OnClickListener {
            getSavedDataObserver();
            if(isMandatoryFieldsFilled()) {
                val bundle = Bundle();
                bundle.putString("seller_name", binding.sellerName.text.toString().trim());
                bundle.putString("total_price", binding.grossPriceData.text.toString().trim());
                bundle.putString("weight", binding.weight.text.toString().trim());
                val navController = Navigation.findNavController(requireActivity(), R.id.fragment)
                navController.navigate(R.id.action_sellProduceFragment_to_soldProduceFragment, bundle)
            }else{
                Toast.makeText(requireContext(),getString(R.string.pls_fill_fields),Toast.LENGTH_SHORT).show();
            }
        })
    }

    /*to chekc whether mandatory fields filled or not*/
    private fun isMandatoryFieldsFilled():Boolean {
        return binding.weight.text.toString().trim().isNotEmpty()
                && binding.sellerName.text.toString().trim().isNotEmpty()
    }

    override fun onKey(view:View?, keyCode:Int, event:KeyEvent?):Boolean {
        if(event!!.getAction() === KeyEvent.ACTION_DOWN) {
            when (keyCode) {
                KeyEvent.KEYCODE_DPAD_CENTER, KeyEvent.KEYCODE_ENTER -> {
                    when(view?.id){
                        R.id.seller_name -> populateLoyaltyCard()
                        R.id.loyalty_card_id -> populateSellerName()
                        else -> {
                            calculateTotalPrice()
                        }
                    }
                    calculateTotalPrice()
                    return true
                }
            }
        }
        return false
    }

    private fun populateSellerName() {
        mSellerNameEntered = false
        viewModel.getSavedData()
    }

    /*to populate loyalty card id*/
    private fun populateLoyaltyCard() {
        mSellerNameEntered = true
        viewModel.getSavedData()
    }

    /*to calculate the total price*/
    @SuppressLint("SetTextI18n")
    private fun calculateTotalPrice() {
        if(isMandatoryFieldsFilled()){
            binding.appliedLoyaltyIndex.visibility = View.VISIBLE
            val calculatedPrice = (binding.appliedLoyaltyIndex.text.toString().replace("Applied loyalty index : ","").toDouble()
                    * binding.weight.text.toString().toDouble() * 1000
                    * (binding.spinnerVillage.selectedItem as VillageData).price)
            val df = DecimalFormat("#.##")
            df.roundingMode = RoundingMode.DOWN
            binding.grossPriceData.text = "${df.format(calculatedPrice)} INR"
        }
    }


    private fun textWatcherForLoyaltyCard() {
        binding.loyaltyCardId.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s:CharSequence, start:Int, before:Int, count:Int) {
            }

            override fun beforeTextChanged(s:CharSequence, start:Int, count:Int, after:Int) {
            }

            override fun afterTextChanged(s:Editable) {
            }
        })
    }

    private fun textWatcherForSellerName() {
        binding.sellerName.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s:CharSequence, start:Int, before:Int, count:Int) {
            }

            override fun beforeTextChanged(s:CharSequence, start:Int, count:Int, after:Int) {
            }

            override fun afterTextChanged(s:Editable) {
            }
        })
    }
}