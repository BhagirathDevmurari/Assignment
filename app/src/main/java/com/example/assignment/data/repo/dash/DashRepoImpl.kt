package com.example.assignment.data.repo.dash

import com.example.assignment.data.beans.Data
import com.example.assignment.data.remote.api.DashApi
import com.example.assignment.data.remote.helper.ApiCallback
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class DashRepoImpl(private val dashApi: DashApi) : DashRepo {
    override fun getTopRatedListAsync(page: Int, apiCallback: ApiCallback<Response<Data>>) {
        apiCallback.onLoading()
        CoroutineScope(Dispatchers.IO).launch {
            val request = dashApi.getTopRatedListAsync(page)
            withContext(Dispatchers.Main) {
                try {
                    val response = request.await()
                    if (response.isSuccessful) {
                        apiCallback.onSuccess(response)
                    } else {
                        apiCallback.onFailed("Something Went Wrong")
                    }

                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        apiCallback.onErrorThrow(e)
                    }
                }
            }
        }
    }

}