package com.example.assignment.data.remote.helper

abstract class ApiCallback<T> {
    abstract fun onSuccess(response: T)
    abstract fun onFailed(response: String)
    abstract fun onErrorThrow(exception: Exception)
    abstract fun onLoading()

}