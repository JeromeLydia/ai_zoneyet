package com.zoneyet.ai_zoneyet.base

import android.app.ProgressDialog
import android.os.Bundle
import android.provider.Settings.Global.getString
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zoneyet.ai_zoneyet.R

abstract class BaseActivity<VB : ViewDataBinding, VM : ViewModel> : AppCompatActivity() {

    protected lateinit var binding: VB
    protected lateinit var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate the layout using Data Binding
        binding = DataBindingUtil.setContentView(this, getLayoutResourceId())

        // Initialize ViewModel
        viewModel = ViewModelProvider(this).get(getViewModelClass())

        // Observe ViewModel live data
        observeViewModel()

        // Initialize views, listeners, and data
        initViews()
        initListeners()
        initData()
    }

    // Abstract methods to be implemented by subclasses
    @LayoutRes
    protected abstract fun getLayoutResourceId(): Int
    protected abstract fun getViewModelClass(): Class<VM>
    protected abstract fun observeViewModel()
    protected abstract fun initViews()
    protected abstract fun initListeners()
    protected abstract fun initData()

    // Show and hide progress dialog
    private lateinit var progressDialog: ProgressDialog

    protected fun showProgressDialog() {
        progressDialog = ProgressDialog.show(this, "", getString(R.string.loading), true)
    }

    protected fun hideProgressDialog() {
        if (::progressDialog.isInitialized && progressDialog.isShowing) {
            progressDialog.dismiss()
        }
    }
}
