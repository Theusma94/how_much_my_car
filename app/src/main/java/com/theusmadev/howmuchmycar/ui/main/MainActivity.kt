package com.theusmadev.howmuchmycar.ui.main

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.theusmadev.howmuchmycar.R
import com.theusmadev.howmuchmycar.BR
import com.theusmadev.howmuchmycar.databinding.ActivityMainBinding
import com.theusmadev.howmuchmycar.di.factory.ViewModelProviderFactory
import com.theusmadev.howmuchmycar.ui.base.BaseActivity
import com.theusmadev.howmuchmycar.utils.NetworkState
import com.theusmadev.howmuchmycar.utils.insertOnStart
import javax.inject.Inject

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    @Inject
    lateinit var factory: ViewModelProviderFactory

    private lateinit var mainViewModel: MainViewModel

    private lateinit var activityMainBinding: ActivityMainBinding

    lateinit var adapter: CarListAdapter

    companion object {
        val TAG = MainActivity::class.java.simpleName
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = getViewDataBinding()
        setupObserver()
        initializeRecyclerView()
        mainViewModel.startFetchBrands()
    }

    private fun initializeRecyclerView() {
        adapter = CarListAdapter()
        activityMainBinding.carsList.adapter = adapter
    }

    private fun setupObserver() {
        mainViewModel.resultBrands.observe(this, Observer {
            when(it.status) {
                    NetworkState.SUCCESS -> {
                        activityMainBinding.brands = it.data?.items?.insertOnStart("Select a Brand")
                        activityMainBinding.spinnerModels.setSelection(0)
                    }
                    NetworkState.LOADING -> {
                        Toast.makeText(this, "Loading Brands", Toast.LENGTH_LONG).show()
                    }
                    NetworkState.ERROR -> {
                        Toast.makeText(this,it.message,Toast.LENGTH_LONG).show()
                    }
                }
        })
        mainViewModel.resultModels.observe(this, Observer {
            when(it.status) {
                NetworkState.SUCCESS -> {
                    activityMainBinding.models = it.data?.items?.insertOnStart("Select a Model")
                    activityMainBinding.spinnerModels.setSelection(0)
                }
                NetworkState.LOADING -> {
                    Toast.makeText(this, "Loading Models", Toast.LENGTH_LONG).show()
                }
                NetworkState.ERROR -> {
                    Toast.makeText(this,it.message,Toast.LENGTH_LONG).show()
                }
            }
        })
        mainViewModel.resultYears.observe(this, Observer {
            when(it.status) {
                NetworkState.SUCCESS -> {
                    activityMainBinding.years = it.data?.items?.insertOnStart("Select a year")
                    activityMainBinding.spinnerYears.setSelection(0)
                }
                NetworkState.LOADING -> {
                    Toast.makeText(this, "Loading Years", Toast.LENGTH_LONG).show()
                }
                NetworkState.ERROR -> {
                    Toast.makeText(this,it.message,Toast.LENGTH_LONG).show()
                }
            }
        })
        mainViewModel.resultListOfCars.observe(this, Observer {
            it?.let {
                adapter.submitList(it.data)
            }
        })

        mainViewModel.getMissedSelectFields().observe(this, Observer {
            if(it) {
                Toast.makeText(this, "Necessário selecionar todos os itens",Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun getViewModel(): MainViewModel {
        mainViewModel = ViewModelProvider(this,factory).get(MainViewModel::class.java)
        return mainViewModel
    }

    override fun getBindingVariable(): Int = BR.viewModel
}
