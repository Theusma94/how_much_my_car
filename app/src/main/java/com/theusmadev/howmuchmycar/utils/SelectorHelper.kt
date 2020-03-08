package com.theusmadev.howmuchmycar.utils

object SelectorHelper {

    var brandSelected: String = ""
    var modelSelected: String = ""
    var yearSelected: String = ""

    var brandsAfterFetch: List<String>? = mutableListOf()
    var modelsAfterFetch: Map<String,List<String>> = mutableMapOf()
    var yearsAfterFetch: Map<String,List<String>> = mutableMapOf()


}