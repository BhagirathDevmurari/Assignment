package com.example.assignment.ui.home

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.example.assignment.BR
import com.example.assignment.R
import com.example.assignment.data.beans.Result
import com.example.assignment.data.remote.helper.Status
import com.example.assignment.databinding.ActivityMainBinding
import com.example.assignment.databinding.RowNowShowingBinding
import com.example.assignment.databinding.RowSliderBinding
import com.example.assignment.di.base.adapter.SimpleRecyclerViewAdapter
import com.example.assignment.di.base.view.AppActivity
import com.example.assignment.di.base.viewmodel.SingleRequestEvent
import com.example.assignment.utils.HorizontalMarginItemDecoration
import java.lang.Math.abs
import com.example.assignment.utils.GridSpacingItemDecoration
import androidx.core.widget.NestedScrollView
import com.example.assignment.ui.detail.DetailActivity
import com.skydoves.transformationlayout.TransformationCompat
import com.skydoves.transformationlayout.TransformationLayout


class MainActivity : AppActivity<ActivityMainBinding, MainActivityVM>() {
    private var sliderItem: SimpleRecyclerViewAdapter<Result, RowSliderBinding>? = null
    private var rcNowShowingItem: SimpleRecyclerViewAdapter<Result, RowNowShowingBinding>? = null
    private var page = 1
    private var isLoadMoreAvailableType = false

    override fun getBindingActivity(): BindingActivity<MainActivityVM> {
        return BindingActivity(R.layout.activity_main, MainActivityVM::class.java)

    }

    override fun subscribeToEvents(vm: MainActivityVM?) {
        Log.e(">>>>", "subscribeToEvents: ")
        viewModel.obrSliderItem.observe(this, SingleRequestEvent.RequestObserver { resource ->
            when (resource.status) {
                Status.LOADING -> {
                    showProgressDialog(R.string.plz_wait)
                }
                Status.SUCCESS -> {

                    dismissProgressDialog()
                    if (isLoadMoreAvailableType) {
                        loadMoreNowShowing(resource.data.results)
                    } else {
                        initSlider(resource.data.results)
                        initNowShowing(resource.data.results)
                    }

                }
                Status.WARN -> {
                    dismissProgressDialog()
                    viewModel.warn.value = resource.message
                }
                Status.ERROR -> {
                    dismissProgressDialog()
                    viewModel.error.value = resource.message
                }
            }
        })
        vm?.getTopRatedList(page)
    }

    private fun loadMoreNowShowing(results: List<Result>?) {
        if (results?.isNotEmpty() == true) {
            isLoadMoreAvailableType = true
            rcNowShowingItem?.addToList(results)
        } else {
            isLoadMoreAvailableType = false
        }

    }

    private fun initSlider(results: List<Result>?) {
        sliderItem = SimpleRecyclerViewAdapter(
            R.layout.row_slider,
            BR.bean,
            object : SimpleRecyclerViewAdapter.SimpleCallback<RowSliderBinding, Result> {
                override fun onItemClick(v: View, m: Result, pos: Int) {
                    super.onItemClick(v, m, pos)
                    val intent = Intent(this@MainActivity, DetailActivity::class.java)
                    intent.putExtra("data",m)
                    TransformationCompat.startActivity(v as TransformationLayout,intent)
                }
            })

        if (results != null) {
            sliderItem?.list = results
            binding.slider.adapter = sliderItem
        }
        binding.slider.offscreenPageLimit = 1
        val nextItemVisiblePx = resources.getDimension(R.dimen.viewpager_next_item_visible)
        val currentItemHorizontalMarginPx = resources.getDimension(R.dimen.viewpager_current_item_horizontal_margin)
        val pageTranslationX = nextItemVisiblePx + currentItemHorizontalMarginPx
        val pageTransformer = ViewPager2.PageTransformer { page: View, position: Float ->
            page.translationX = -pageTranslationX * position
            page.scaleY = 1 - (0.20f * abs(position))
        }
        binding.slider.setPageTransformer(pageTransformer)
        val itemDecoration = HorizontalMarginItemDecoration(this, R.dimen.viewpager_current_item_horizontal_margin)
        binding.slider.addItemDecoration(itemDecoration)

    }

    private fun initNowShowing(results: List<Result>?) {
        isLoadMoreAvailableType = true
        rcNowShowingItem = SimpleRecyclerViewAdapter(
            R.layout.row_now_showing,
            BR.bean,
            object : SimpleRecyclerViewAdapter.SimpleCallback<RowNowShowingBinding, Result> {
                override fun onItemClick(v: View, m: Result, pos: Int) {
                    super.onItemClick(v, m, pos)
                    val intent = Intent(this@MainActivity, DetailActivity::class.java)
                    intent.putExtra("data",m)
                    TransformationCompat.startActivity(v as TransformationLayout,intent)
                }
            })
        val spanCount = 3

        val spacing = 35
        val includeEdge = true
        binding.rcNowShowing.addItemDecoration(GridSpacingItemDecoration(spanCount, spacing, includeEdge))

        if (results != null) {
            rcNowShowingItem?.list = results
            binding.rcNowShowing.adapter = rcNowShowingItem
        }
        binding.scrollMain.setOnScrollChangeListener { v: NestedScrollView, scrollX: Int, scrollY: Int, oldScrollX: Int, oldScrollY: Int ->
            if (v.getChildAt(v.childCount - 1) != null) {
                if (scrollY >= v.getChildAt(v.childCount - 1).measuredHeight - v.measuredHeight &&
                    scrollY > oldScrollY
                ) {
                    page++
                    if (isLoadMoreAvailableType) viewModel?.getTopRatedList(page)
                }
            }
        }
    }
}