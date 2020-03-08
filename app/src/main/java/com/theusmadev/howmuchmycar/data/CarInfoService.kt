package com.theusmadev.howmuchmycar.data

import com.theusmadev.howmuchmycar.data.model.*
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

    @GET("brands/{brand}/models/{model}/years/{year}/versions")
    fun getVersionsIDs(@Path("brand") brand: String,
                       @Path("model") model: String,
                       @Path("year") year: String): Call<List<VersionIdResponse>>

    @GET("brands/{brand}/models/{model}/years/{year}/versions/{versionId}")
    fun getCarInfos(@Path("brand") brand: String,
                    @Path("model") model: String,
                    @Path("year") year: String,
                    @Path("versionId") versionId: String): Call<CarInfoResponse>

}
