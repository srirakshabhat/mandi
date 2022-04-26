package com.project.mandi.presentation.ui


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.project.mandi.R
import com.project.mandi.presentation.ui.viewmodel.SellProduceViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: SellProduceViewModelFactory
    lateinit var viewModel:SellProduceViewModel

    override fun onCreate(savedInstanceState:Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this,factory).get(SellProduceViewModel::class.java)
    }
}