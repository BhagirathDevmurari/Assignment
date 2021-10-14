package com.example.assignment.data.repo.dash

import com.example.assignment.data.beans.Data
import com.example.assignment.data.remote.helper.ApiCallback
import retrofit2.Response

interface DashRepo {
    fun getTopRatedListAsync(page:Int, apiCallback: ApiCallback<Response<Data>>)

}