package com.theusmadev.howmuchmycar.data.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.theusmadev.howmuchmycar.data.CarInfoService
import com.theusmadev.howmuchmycar.data.model.BrandsResponse
import com.theusmadev.howmuchmycar.data.model.ModelResponse
import com.theusmadev.howmuchmycar.data.model.YearsResponse
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
}
