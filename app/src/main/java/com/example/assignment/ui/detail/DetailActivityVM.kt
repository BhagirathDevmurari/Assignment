package com.example.assignment.ui.detail

import com.example.assignment.data.beans.Data
import com.example.assignment.data.remote.helper.ApiCallback
import com.example.assignment.data.remote.helper.NetworkErrorHandler
import com.example.assignment.data.remote.helper.Resource
import com.example.assignment.data.repo.dash.DashRepo
import com.example.assignment.di.base.viewmodel.BaseViewModel
import com.example.assignment.di.base.viewmodel.SingleRequestEvent
import retrofit2.Response
import javax.inject.Inject

class DetailActivityVM @Inject constructor(
    private val dashRepo: DashRepo,
    private val networkErrorHandler: NetworkErrorHandler
) : BaseViewModel() {

    val obrSliderItem = SingleRequestEvent<Data>()

    fun getTopRatedList(page: Int) {
        dashRepo.getTopRatedListAsync(page, object : ApiCallback<Response<Data>>() {
            override fun onSuccess(response: Response<Data>) {
                obrSliderItem.value = Resource.success(response.body(), "Ok")
            }

            override fun onFailed(message: String) {
                obrSliderItem.value = Resource.error(null, message)
            }

            override fun onErrorThrow(exception: Exception) {
                obrSliderItem.value = Resource.error(null, networkErrorHandler.getErrMsg(exception))
            }

            override fun onLoading() {
                obrSliderItem.value = Resource.loading(null, "")
            }
        })
    }
}