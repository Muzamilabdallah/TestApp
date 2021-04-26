package com.altkamul.testapp.ui.viewModel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.altkamul.testapp.model.Articles
import com.altkamul.testapp.repository.RemoteRepository
import kotlinx.coroutines.launch


/*************************
 * Created by AZAT SAYAN *
 *                       *
 * Contact: @theazat     *
 *                       *
 * 23/08/2020 - 10:28 AM *
 ************************/
class NewsViewModel @ViewModelInject constructor(
    private val remoteRepository: RemoteRepository,
) : ViewModel() {

    val newsLiveData = MutableLiveData<MutableList<Articles>>()

    init {
        getAllNews()
    }

    fun getAllNews() = viewModelScope.launch {
        val allNews = remoteRepository.getAllNews()
        newsLiveData.postValue(allNews)
    }




}