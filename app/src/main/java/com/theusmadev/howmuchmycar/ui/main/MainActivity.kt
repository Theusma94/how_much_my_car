package com.theusmadev.howmuchmycar.ui.main

import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.lifecycle.ViewModelProvider
import com.theusmadev.howmuchmycar.R
import com.theusmadev.howmuchmycar.BR
import com.theusmadev.howmuchmycar.databinding.ActivityMainBinding
import com.theusmadev.howmuchmycar.di.factory.ViewModelProviderFactory
import kotlinx.android.synthetic.main.activity_main.*
import com.theusmadev.howmuchmycar.ui.base.BaseActivity
import javax.inject.Inject

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    @Inject
    lateinit var factory: ViewModelProviderFactory

    private lateinit var mainViewModel: MainViewModel

    private lateinit var activityMainBinding: ActivityMainBinding

    companion object {
        val TAG = MainActivity::class.java.simpleName
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = getViewDataBinding()
        text_sample.text = "How Much my car?"
    }

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun getViewModel(): MainViewModel {
        mainViewModel = ViewModelProvider(this,factory).get(MainViewModel::class.java)
        return mainViewModel
    }

    override fun getBindingVariable(): Int = BR.viewModel
}
