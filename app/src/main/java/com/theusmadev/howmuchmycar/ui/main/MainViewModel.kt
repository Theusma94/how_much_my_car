package com.theusmadev.howmuchmycar.ui.main



import android.view.View
import android.widget.Spinner
import android.widget.TextView
import androidx.lifecycle.*
import com.theusmadev.howmuchmycar.data.remote.CarRepository
import com.theusmadev.howmuchmycar.utils.AbsentLiveData
import com.theusmadev.howmuchmycar.utils.SelectorHelper
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val carRepository: CarRepository,
    private val selectorHelper: SelectorHelper): ViewModel() {

    private val isNeedToFetchBrands: MutableLiveData<Boolean> = MutableLiveData()
    private val isNeedToFetchModels: MutableLiveData<Boolean> = MutableLiveData()
    private val isNeedToFetchYears: MutableLiveData<Boolean> = MutableLiveData()
    private val isNeedToFetchCars: MutableLiveData<Boolean> = MutableLiveData()

    fun setBrandSelected(brandSelected: View, position: Int, spinnerModels: Spinner,spinnerYears: Spinner ) {
        if(position > 0) {
            spinnerModels.adapter = null
            spinnerYears.adapter = null
            selectorHelper.brandSelected  = (brandSelected as TextView).text.toString()
            startFetchModels()
        } else {
            selectorHelper.brandSelected = ""
        }
    }
    fun setModelSelected(modelSelected: View, position: Int) {
        if(position > 0) {
            selectorHelper.modelSelected = (modelSelected as TextView).text.toString()
            startFetchYears()
        } else {
            selectorHelper.modelSelected = ""
        }
    }
    fun setYearSelected(yearSelected: View, position: Int) {
        if(position > 0) {
            selectorHelper.yearSelected = (yearSelected as TextView).text.toString()
        } else {
            selectorHelper.yearSelected = ""
        }
    }

    fun onClickSearchButton() {
        if(selectorHelper.isReadyToFetchCars()) {
            isNeedToFetchCars.value = true
        } else {}
    }

    fun startFetchBrands() {
        isNeedToFetchBrands.value = true
    }

    private fun startFetchModels() {
        isNeedToFetchModels.value = true
    }

    private fun startFetchYears() {
        isNeedToFetchYears.value = true
    }

    val resultBrands = isNeedToFetchBrands.switchMap {
        if(it) {
            carRepository.getBrands()
        } else AbsentLiveData.create()
    }
    val resultModels = isNeedToFetchModels.switchMap {
        if(it) {
            carRepository.getModels()
        } else AbsentLiveData.create()
    }
    val resultYears = isNeedToFetchYears.switchMap {
        if(it) {
            carRepository.getYears()
        } else AbsentLiveData.create()
    }

    val resultListOfCars = isNeedToFetchCars.switchMap {
        if(it) {
            carRepository.getCars()
        } else AbsentLiveData.create()
    }

}