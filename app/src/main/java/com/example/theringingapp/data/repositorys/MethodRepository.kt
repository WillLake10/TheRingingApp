package com.example.theringingapp.data.repositorys

import androidx.lifecycle.LiveData
import com.example.theringingapp.data.dao.MethodDao
import com.example.theringingapp.data.dto.Method

class MethodRepository(private val methodDao: MethodDao) {

    val allWords: LiveData<List<Method>> = methodDao.getAlphabetizedMethods()

    suspend fun insert(method: Method) {
        methodDao.insert(method)
    }
}