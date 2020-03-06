package com.theusmadev.howmuchmycar.utils


data class ResultFetch<out T>(val status: NetworkState, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T?): ResultFetch<T> {
            return ResultFetch(NetworkState.SUCCESS, data, null)
        }

        fun <T> error(msg: String, data: T?): ResultFetch<T> {
            return ResultFetch(NetworkState.ERROR, data, msg)
        }

        fun <T> loading(data: T?): ResultFetch<T> {
            return ResultFetch(NetworkState.LOADING, data, null)
        }
    }
}