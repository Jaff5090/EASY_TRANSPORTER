package com.example.easy_transport.viewmodel


import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.easy_transport.repository.OnBoardingRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class OnBoardingViewModel(private val repository: OnBoardingRepository) : ViewModel() {

    private val _isOnBoardingCompleted = MutableStateFlow(false)
    val isOnBoardingCompleted: StateFlow<Boolean> = _isOnBoardingCompleted

    init {
        viewModelScope.launch {
            _isOnBoardingCompleted.value = repository.getOnBoardingCompleted()
        }
    }

    fun setOnBoardingCompleted() {
        viewModelScope.launch {
            repository.setOnBoardingCompleted()
            _isOnBoardingCompleted.value = true
        }
    }
}

class OnBoardingViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(OnBoardingViewModel::class.java)) {
            val repository = OnBoardingRepository(context)
            @Suppress("UNCHECKED_CAST")
            return OnBoardingViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
