package com.example.assignment.di.base.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.CallSuper;
import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.example.assignment.BR;
import com.example.assignment.data.remote.spinner.SpinnerLoader;
import com.example.assignment.data.repo.dash.DashRepo;
import com.example.assignment.di.base.MyApplication;
import com.example.assignment.di.base.viewmodel.BaseViewModel;
import com.example.assignment.ui.detail.TransformationAppCompatActivity;
import com.skydoves.transformationlayout.TransformationCompat;

import java.util.Objects;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public abstract class BaseActivity<B extends ViewDataBinding, V extends BaseViewModel> extends TransformationAppCompatActivity {
    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    protected V viewModel;
    protected B binding;
    @Nullable
    private SpinnerLoader progressDialog;

    @Inject
    DashRepo dashRepo;


    protected abstract BindingActivity<V> getBindingActivity();

    @CallSuper
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TransformationCompat.onTransformationStartContainer(this);
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(MyApplication.getInstance()).setCurrentActivity(this);
        Objects.requireNonNull(MyApplication.getInstance()).setAuthRepo(dashRepo);
        BindingActivity<V> bindingActivity = getBindingActivity();
        if (bindingActivity == null) {
            throw new NullPointerException("Binding activity cannot be null");
        }
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(bindingActivity.getClazz());
        binding = DataBindingUtil.setContentView(this, bindingActivity.getLayoutResId());
        binding.setVariable(BR.vm, viewModel);
        subscribeToEvents(viewModel);

    }


    protected abstract void subscribeToEvents(V vm);

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }


    protected final void showProgressDialog(@Nullable String msg) {
        if (progressDialog == null) {
            progressDialog = new SpinnerLoader(this);
        }
        progressDialog.show();
    }

    protected final void showProgressDialog(@StringRes int msgResId) {
        showProgressDialog(getString(msgResId));
    }


    /**
     * Dismiss progress dialog
     */
    protected final void dismissProgressDialog() {
        if (progressDialog != null && this.progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    protected static class BindingActivity<V extends BaseViewModel> {
        @LayoutRes
        private int layoutResId;
        private Class<V> clazz;

        public BindingActivity(@LayoutRes int layoutResId, Class<V> clazz) {
            this.layoutResId = layoutResId;
            this.clazz = clazz;
        }

        int getLayoutResId() {
            return layoutResId;
        }

        Class<V> getClazz() {
            return clazz;
        }
    }

    protected void startNewActivity(Intent intent, boolean finishExisting) {
        startNewActivity(intent, finishExisting, true);
    }

    protected void startNewActivity(Intent intent, boolean finishExisting, boolean animate) {
        startActivity(intent);
        if (finishExisting)
            finish();

    }

    protected void startNewActivity(Intent intent) {
        startNewActivity(intent, false, true);
    }


}