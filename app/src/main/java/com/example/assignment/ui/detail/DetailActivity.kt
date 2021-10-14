package com.example.assignment.ui.detail

import android.content.Context
import android.content.Intent
import com.example.assignment.R
import com.example.assignment.data.beans.Result
import com.example.assignment.databinding.ActivityDetailBinding
import com.example.assignment.di.base.view.AppActivity

class DetailActivity : AppActivity<ActivityDetailBinding, DetailActivityVM>() {

    override fun getBindingActivity(): BindingActivity<DetailActivityVM> {
        return BindingActivity(R.layout.activity_detail, DetailActivityVM::class.java)
    }

    override fun subscribeToEvents(vm: DetailActivityVM?) {
        val data = intent.extras?.get("data") as Result
        if (data.voteAverage != 0.0) {
            data.ratting = ((data.voteAverage!! * 5) / 10).toFloat()
        } else {
            data.ratting = 0F
        }
        binding.bean = data
        viewModel.obrClick.observe(this, { view ->
            when (view.id) {
                R.id.imgBack -> {
                    onBackPressed()
                }
            }
        })

    }
}