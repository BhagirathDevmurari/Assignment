package com.example.assignment.data.remote.api

import com.example.assignment.data.beans.Data
import com.example.assignment.data.beans.EndPoints
import com.example.assignment.data.remote.helper.ApiCallback
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.*

interface DashApi {
    @GET(EndPoints.Home.topRated)
    fun getTopRatedListAsync(@Query("page") page: Int): Deferred<Response<Data>>
}