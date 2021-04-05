package com.muratguc.roofstack.data.model

/**
 * Created by Murat Güç on 2/1/2021.
 */

data class Resource<out T>(val status: Status, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T): Resource<T> =
            Resource(status = SUCCESS, data = data, message = null)

        fun <T> error(data: T?, message: String): Resource<T> =
            Resource(status = ERROR, data = data, message = message)

        fun <T> loading(data: T?): Resource<T> =
            Resource(status = LOADING, data = data, message = null)
    }
}

sealed class Status
object ERROR : Status()
object SUCCESS : Status()
object LOADING : Status()