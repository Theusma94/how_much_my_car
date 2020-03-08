package com.theusmadev.howmuchmycar.data.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataScope
import androidx.lifecycle.liveData
import com.theusmadev.howmuchmycar.data.CarInfoService
import com.theusmadev.howmuchmycar.data.model.*
import com.theusmadev.howmuchmycar.utils.ResultFetch
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException

class CarRepository(private val carInfoService: CarInfoService) {

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

    fun getModels(brand: String): LiveData<ResultFetch<ModelResponse>> {
        return liveData {
            emit(ResultFetch.loading(null))
            try {
                emit(fetchModels(brand))
            } catch(exception: Exception) {
                emit(ResultFetch.error(exception.message!!,null));
            }
        }
    }

    private suspend fun fetchModels(brand: String): ResultFetch<ModelResponse> {
        return try {
            val response = withContext(Dispatchers.IO) {carInfoService.getModels(brand).execute() }
            if (response.isSuccessful) {
                val modelResponse = ModelResponse()
                modelResponse.items = response.body()
                ResultFetch.success(modelResponse)
            } else ResultFetch.error(response.code().toString(),null)
        } catch (e: IOException) {
            ResultFetch.error(e.message!!,null);
        }
    }

    fun getYears(brand: String,model: String): LiveData<ResultFetch<YearsResponse>> {
        return liveData {
            emit(ResultFetch.loading(null))
            try {
                emit(fetchYears(brand,model))
            } catch(exception: Exception) {
                emit(ResultFetch.error(exception.message!!,null));
            }
        }
    }

    private suspend fun fetchYears(brand: String,model: String): ResultFetch<YearsResponse> {
        return try {
            val response = withContext(Dispatchers.IO) { carInfoService.getYears(brand,model).execute() }
            if (response.isSuccessful) {
                val yearsResponse = YearsResponse()
                yearsResponse.items = response.body()
                ResultFetch.success(yearsResponse)
            } else ResultFetch.error(response.code().toString(),null)
        } catch (e: IOException) {
            ResultFetch.error(e.message!!,null);
        }
    }

    fun getCars(brand: String,model: String,year: String): LiveData<ResultFetch<List<CarInfoResponse>>> {
        return liveData {
            emit(ResultFetch.loading(null))
            try {
                val versionsIds = fetchVersionsIds(brand,model,year)
                emit(fetchCars(brand,model,year,versionsIds))
            } catch (exception: Exception) {
                emit(ResultFetch.error(exception.message!!,null))
            }
        }
    }

    private suspend fun fetchCars(brand: String, model: String, year: String,versionsIds: List<VersionIdResponse>?): ResultFetch<List<CarInfoResponse>> {
        val listOfCars = mutableListOf<CarInfoResponse>()
        return try {
            for(versionId in versionsIds!!) {
                val response = withContext(Dispatchers.IO) { carInfoService.getCarInfos(brand,model,year,versionId.versionId).execute() }
                if (response.isSuccessful) {
                    listOfCars.add(response.body()!!)
                }
            }
            ResultFetch.success(listOfCars)
        } catch (e: IOException) {
            ResultFetch.error(e.message!!,null);
        }
    }

    private suspend fun fetchVersionsIds(brand: String, model: String, year: String): List<VersionIdResponse>? {
        return try {
            val response = withContext(Dispatchers.IO) { carInfoService.getVersionsIDs(brand, model, year).execute() }
            if(response.isSuccessful) {
                response.body()
            } else throw IOException()
        } catch (e: IOException) {
            throw IOException(e)
        }
    }
}
