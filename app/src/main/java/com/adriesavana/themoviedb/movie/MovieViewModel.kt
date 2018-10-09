package com.adriesavana.themoviedb.movie

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.adriesavana.themoviedb.common.base.ViewModelType
import javax.inject.Inject

interface MovieActivityViewModelType : ViewModelType

class MovieViewModel : ViewModel(), MovieActivityViewModelType {

    @Suppress("UNCHECKED_CAST")
    class Factory
    @Inject constructor() : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MovieViewModel::class.java)) {
                return MovieViewModel() as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}