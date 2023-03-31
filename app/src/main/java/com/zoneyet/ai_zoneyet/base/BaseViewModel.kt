package com.zoneyet.ai_zoneyet.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Response

abstract class BaseViewModel : ViewModel() {
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage


    protected fun <T : Any> executeRequest(
        apiCall: suspend () -> T,
        liveData: MutableLiveData<T>,
        loadingLiveData: MutableLiveData<Boolean>,
        errorLiveData: MutableLiveData<String>
    ) {
        viewModelScope.launch {
            try {
                loadingLiveData.postValue(true)
                val result = apiCall.invoke()
                liveData.postValue(result)
            } catch (e: Exception) {
                errorLiveData.postValue(e.message)
            } finally {
                loadingLiveData.postValue(false)
            }
        }
    }


    protected fun <T> handleResponse(
        response: Response<T>,
        onSuccess: (T?) -> Unit,
        onError: (String?) -> Unit
    ) {
        _isLoading.value = false
        when (response.code()) {
            200 -> onSuccess(response.body())
            else -> onError(response.errorBody()?.string())
        }
    }

    protected fun handleError(throwable: Throwable, onError: (String?) -> Unit) {
        _isLoading.value = false
        _errorMessage.value = throwable.message
        onError(throwable.message)
    }
}
