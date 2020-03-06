package com.theusmadev.howmuchmycar.data

import com.theusmadev.howmuchmycar.data.model.BrandsResponse
import com.theusmadev.howmuchmycar.data.model.CarInfoResponse
import com.theusmadev.howmuchmycar.data.model.ModelResponse
import com.theusmadev.howmuchmycar.data.model.YearsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface CarInfoService {

    @GET("brands")
    fun getBrands(): Call<List<String>>

    @GET("brands/{brand}/models")
    fun getModels(@Path("brand") brand: String): Call<List<String>>

    @GET("brands/{brand}/models/{model}/years")
    fun getYears(@Path("brand") brand: String,
                 @Path("model") model: String): Call<List<String>>

    @GET("brands/{brand}/models/{model}/years")
    fun getCarInfos(@Path("brand") brand: String,
                    @Path("model") model: String,
                    @Path("year") year: String): Call<CarInfoResponse>

}
