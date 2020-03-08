package com.theusmadev.howmuchmycar.data.model

import com.google.gson.annotations.SerializedName

data class CarInfoResponse(

    @SerializedName("precoMinimo")
    var precoMinimo: Int,

    @SerializedName("precoMaximo")
    var precoMaximo: Int,

    @SerializedName("brand")
    var brand: String,

    @SerializedName("model")
    var model: String,

    @SerializedName("modelYear")
    var year: String,

    @SerializedName("version")
    var version: String,

    @SerializedName("versionId")
    var versionId: String
)
