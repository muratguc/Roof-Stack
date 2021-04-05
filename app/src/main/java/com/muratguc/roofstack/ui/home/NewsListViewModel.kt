package com.muratguc.roofstack.ui.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.muratguc.roofstack.common.SingleLiveEvent
import com.muratguc.roofstack.common.convertLongToTime
import com.muratguc.roofstack.data.datasourse.NewsListPagingDataSource
import com.muratguc.roofstack.domain.usecase.NewsListUseCase
import java.util.Calendar
import java.util.concurrent.TimeUnit

class NewsListViewModel @ViewModelInject constructor(
    private val newsListUseCase: NewsListUseCase
) :
    ViewModel() {
    private val now = Calendar.getInstance()

    var from: SingleLiveEvent<Long> = SingleLiveEvent()
    var to: SingleLiveEvent<Long> = SingleLiveEvent()

    init {
        from.value = (now.timeInMillis - TimeUnit.DAYS.toMillis(10))
        to.value = now.timeInMillis
    }

    fun fetchNews() = Pager(PagingConfig(pageSize = 20)) {
        NewsListPagingDataSource(newsListUseCase, convertLongToTime(from.value), convertLongToTime(to.value))
    }.flow.cachedIn(viewModelScope).asLiveData()
}