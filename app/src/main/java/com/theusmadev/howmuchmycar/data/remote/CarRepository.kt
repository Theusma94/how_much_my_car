package com.theusmadev.howmuchmycar.data.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataScope
import androidx.lifecycle.liveData
import com.theusmadev.howmuchmycar.data.CarInfoService
import com.theusmadev.howmuchmycar.data.model.*
import com.theusmadev.howmuchmycar.utils.ResultFetch
import com.theusmadev.howmuchmycar.utils.SelectorHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException

class CarRepository(private val carInfoService: CarInfoService,
                    private val selectorHelper: SelectorHelper) {


    fun getBrands(): LiveData<ResultFetch<BrandsResponse>> {
        return liveData {
            emit(ResultFetch.loading(null))
            try {
                emit(fetchBrands())
            } catch(exception: Exception) {
                emit(ResultFetch.error(exception.message!!,null));
            }
        }
    }

    private suspend fun fetchBrands(): ResultFetch<BrandsResponse> {
        return try {
            val response = withContext(Dispatchers.IO) {carInfoService.getBrands().execute() }
            if (response.isSuccessful) {
                val brandsResponse = BrandsResponse()
                brandsResponse.items = response.body()
                ResultFetch.success(brandsResponse)
            } else ResultFetch.error(response.code().toString(),null)
        } catch (e: IOException) {
            ResultFetch.error(e.message!!,null);
        }
    }

    fun getModels(): LiveData<ResultFetch<ModelResponse>> {
        return liveData {
            emit(ResultFetch.loading(null))
            try {
                emit(fetchModels())
            } catch(exception: Exception) {
                emit(ResultFetch.error(exception.message!!,null));
            }
        }
    }

    private suspend fun fetchModels(): ResultFetch<ModelResponse> {
        return try {
            val response = withContext(Dispatchers.IO) {
                carInfoService.getModels(selectorHelper.brandSelected).execute()
            }
            if (response.isSuccessful) {
                val modelResponse = ModelResponse()
                modelResponse.items = response.body()
                ResultFetch.success(modelResponse)
            } else ResultFetch.error(response.code().toString(),null)
        } catch (e: IOException) {
            ResultFetch.error(e.message!!,null);
        }
    }

    fun getYears(): LiveData<ResultFetch<YearsResponse>> {
        return liveData {
            emit(ResultFetch.loading(null))
            try {
                emit(fetchYears())
            } catch(exception: Exception) {
                emit(ResultFetch.error(exception.message!!,null));
            }
        }
    }

    private suspend fun fetchYears(): ResultFetch<YearsResponse> {
        return try {
            val response = withContext(Dispatchers.IO) { carInfoService.getYears(selectorHelper.brandSelected,selectorHelper.modelSelected).execute() }
            if (response.isSuccessful) {
                val yearsResponse = YearsResponse()
                yearsResponse.items = response.body()
                ResultFetch.success(yearsResponse)
            } else ResultFetch.error(response.code().toString(),null)
        } catch (e: IOException) {
            ResultFetch.error(e.message!!,null);
        }
    }

    fun getCars(): LiveData<ResultFetch<List<CarInfoResponse>>> {
        return liveData {
            emit(ResultFetch.loading(null))
            try {
                val versionsIds = fetchVersionsIds()
                emit(fetchCars(versionsIds))
            } catch (exception: Exception) {
                emit(ResultFetch.error(exception.message!!,null))
            }
        }
    }

    private suspend fun fetchCars(versionsIds: List<VersionIdResponse>?): ResultFetch<List<CarInfoResponse>> {
        val listOfCars = mutableListOf<CarInfoResponse>()
        return try {
            for(versionId in versionsIds!!) {
                val response = withContext(Dispatchers.IO) {
                    carInfoService.getCarInfos(selectorHelper.brandSelected,
                        selectorHelper.modelSelected,
                        selectorHelper.yearSelected,
                        versionId.versionId).execute()
                }
                if (response.isSuccessful) {
                    listOfCars.add(response.body()!!)
                }
            }
            ResultFetch.success(listOfCars)
        } catch (e: IOException) {
            ResultFetch.error(e.message!!,null);
        }
    }

    private suspend fun fetchVersionsIds(): List<VersionIdResponse>? {
        return try {
            val response = withContext(Dispatchers.IO) {
                carInfoService.getVersionsIDs(
                    selectorHelper.brandSelected,
                    selectorHelper.modelSelected,
                    selectorHelper.yearSelected).execute()
            }
            if(response.isSuccessful) {
                response.body()
            } else throw IOException()
        } catch (e: IOException) {
            throw IOException(e)
        }
    }
}
