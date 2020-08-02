package com.example.theringingapp.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.theringingapp.data.dto.Method
import com.example.theringingapp.data.repositorys.MethodRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MethodViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: MethodRepository
    val allMethods: LiveData<List<Method>>

    init {
        val methodsDao = MethodRoomDatabase.getDatabase(application, viewModelScope).methodDao()
        repository = MethodRepository(methodsDao)
        allMethods = repository.allWords
    }

    fun insert(method: Method) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(method)
    }
}