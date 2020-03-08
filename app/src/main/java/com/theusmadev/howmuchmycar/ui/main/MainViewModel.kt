package com.theusmadev.howmuchmycar.ui.main



import android.view.View
import android.widget.Spinner
import android.widget.TextView
import androidx.lifecycle.*
import com.theusmadev.howmuchmycar.data.remote.CarRepository
import com.theusmadev.howmuchmycar.utils.AbsentLiveData
import javax.inject.Inject
class MainViewModel @Inject constructor(val carRepository: CarRepository): ViewModel() {

    private val isNeedToFetchBrands: MutableLiveData<Boolean> = MutableLiveData()
    private val isNeedToFetchModels: MutableLiveData<Boolean> = MutableLiveData()
    private val isNeedToFetchYears: MutableLiveData<Boolean> = MutableLiveData()
    private val isNeedToFetchCars: MutableLiveData<Boolean> = MutableLiveData()
    private var brand: String = ""
    private var model: String = ""
    private var year: String = ""

    fun setBrandSelected(brandSelected: View, position: Int, spinnerModels: Spinner,spinnerYears: Spinner ) {
        if(position > 0) {
            spinnerModels.adapter = null
            spinnerYears.adapter = null
            brand = (brandSelected as TextView).text.toString()
            startFetchModels()
        }
    }
    fun setModelSelected(modelSelected: View, position: Int) {
        if(position > 0) {
            model = (modelSelected as TextView).text.toString()
            startFetchYears()
        }
    }
    fun setYearSelected(yearSelected: View, position: Int) {
        if(position > 0) {
            year = (yearSelected as TextView).text.toString()
        }
    }

    fun onClickSearchButton() {
        isNeedToFetchCars.value = true
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
            carRepository.getModels(brand)
        } else AbsentLiveData.create()
    }
    val resultYears = isNeedToFetchYears.switchMap {
        if(it) {
            carRepository.getYears(brand,model)
        } else AbsentLiveData.create()
    }

    val resultListOfCars = isNeedToFetchCars.switchMap {
        if(it) {
            carRepository.getCars(brand,model, year)
        } else AbsentLiveData.create()
    }

}