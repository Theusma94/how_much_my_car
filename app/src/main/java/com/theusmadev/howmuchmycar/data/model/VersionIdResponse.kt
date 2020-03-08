package com.theusmadev.howmuchmycar.data.model

import com.google.gson.annotations.SerializedName

data class VersionIdResponse (

    @SerializedName("versionId")
    var versionId: String = ""
)