package com.example.happylife.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.happylife.bean.SimpleNews
import com.example.happylife.datasource.factory.NewsListDataSourceFactory

/**
 * @author by syt
 * on 2021/8/16
 * desc:
 */
class NewsListViewModel : ViewModel() {
    //PagedList.Config提供分页需要的参数
    var pagedListLiveData = LivePagedListBuilder<Int, SimpleNews>(
        NewsListDataSourceFactory(viewModelScope),
        PagedList.Config.Builder().setPageSize(20).build()
    ).build()

    /* 下拉刷新
     * 用户进行下拉刷新的时候通过只需要调用invalidate方法，
     * LiveData会重新生成一个新的PagedList，这个PagedList会委托DataSource去请求新的数据
     */
    fun resetQuery() {
        pagedListLiveData.value?.dataSource?.invalidate()
    }
}