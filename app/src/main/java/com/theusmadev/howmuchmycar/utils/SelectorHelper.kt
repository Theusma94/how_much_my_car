package com.theusmadev.howmuchmycar.utils

data class SelectorHelper(
    var brandSelected: String = "",
    var modelSelected: String = "",
    var yearSelected: String = ""
) {
    fun isReadyToFetchCars(): Boolean {
        return !(brandSelected.isEmpty() ||
                modelSelected.isEmpty() ||
                yearSelected.isEmpty())
    }

    fun clearAllFields() {
        brandSelected = ""
        modelSelected = ""
        yearSelected = ""
    }
}