package com.theusmadev.howmuchmycar.utils

fun <T> List<T>.insertOnStart(element: T): List<T> {
    val shiftedList: MutableList<T> = mutableListOf(element)
    for(originalElement in this) {
        shiftedList.add(originalElement)
    }
    return shiftedList
}