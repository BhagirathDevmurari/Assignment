package com.example.assignment.ui.detail

import android.os.Handler
import android.os.Looper
import android.view.Gravity
import android.view.View
import androidx.transition.Slide
import androidx.transition.TransitionManager
import com.example.assignment.R
import com.example.assignment.data.beans.Result
import com.example.assignment.databinding.ActivityDetailBinding
import com.example.assignment.di.base.view.AppActivity

class DetailActivity : AppActivity<ActivityDetailBinding, DetailActivityVM>() {

    override fun getBindingActivity(): BindingActivity<DetailActivityVM> {
        return BindingActivity(R.layout.activity_detail, DetailActivityVM::class.java)
    }

    override fun subscribeToEvents(vm: DetailActivityVM?) {
        val slide = Slide()
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

        Handler(Looper.myLooper()!!).postDelayed({
            slide.slideEdge = Gravity.END;
            TransitionManager.beginDelayedTransition(binding.cnsAnimate, slide);
            binding.txtMoovieName.visibility = View.VISIBLE;
            binding.txtReleaseDate.visibility = View.VISIBLE;
            binding.textView.visibility = View.VISIBLE;
            binding.textView2.visibility = View.VISIBLE;
            binding.rating.visibility = View.VISIBLE;
            TransitionManager.beginDelayedTransition(binding.relativeLayout, slide);
            binding.relativeLayout.visibility = View.VISIBLE;
        }, 500)

    }
}