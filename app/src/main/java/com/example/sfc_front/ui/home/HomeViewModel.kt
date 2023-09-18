package com.example.sfc_front.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val _progressInt = MutableLiveData<Int>()
    val progressInt: LiveData<Int>
        get() = _progressInt

    private var job: Job? = null
    private var currentProgress = 0

    fun startTask() {
        if (!isTaskRunning()) {
            job = GlobalScope.launch {
                while (currentProgress <= 100) {
                    _progressInt.postValue(currentProgress)
                    currentProgress++
                    delay(60) // 200 毫秒（0.2秒）延迟
                }
            }
        }
    }

    fun isTaskRunning(): Boolean {
        return job?.isActive == true
    }

    fun cancelTask() {
        job?.cancel()
    }

    fun getCurrentProgress(): Int {
        return currentProgress
    }

    fun setCurrentProgress(progress: Int) {
        currentProgress = progress
    }
}
