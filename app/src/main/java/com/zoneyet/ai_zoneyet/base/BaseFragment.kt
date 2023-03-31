package com.zoneyet.ai_zoneyet.base

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zoneyet.ai_zoneyet.R

abstract class BaseFragment<VB : ViewDataBinding, VM : ViewModel> : Fragment() {

    protected lateinit var binding: VB
    protected lateinit var viewModel: VM

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout using Data Binding
        binding = DataBindingUtil.inflate(inflater, getLayoutResourceId(), container, false)

        // Initialize ViewModel
        viewModel = ViewModelProvider(this).get(getViewModelClass())

        // Observe ViewModel live data
        observeViewModel()

        // Initialize views, listeners, and data
        initViews()
        initListeners()
        initData()

        // Return the root view of the Data Binding layout
        return binding.root
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
        progressDialog = ProgressDialog.show(requireContext(), "", getString(R.string.loading), true)
    }

    protected fun hideProgressDialog() {
        if (::progressDialog.isInitialized && progressDialog.isShowing) {
            progressDialog.dismiss()
        }
    }
}
