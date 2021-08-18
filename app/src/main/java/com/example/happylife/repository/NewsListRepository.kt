package com.example.happylife.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.happylife.api.NewsService
import com.example.happylife.bean.SimpleNews
import com.example.happylife.pagingsource.NewsPagingSource
import kotlinx.coroutines.flow.Flow

/**
 * @author by syt
 * on 2021/8/16
 * desc:仓库层要做的工作是自主判断接口请求的数据应该是从数据库中读取还是从网络中获取，并将数据返回给调用方。
 * 如果是从网络中获取的话还要将这些数据存入到数据库当中，以避免下次重复从网络中获取。
 * 简而言之，仓库的工作就是在本地和网络数据之间做一个分配和调度的工作，
 * 调用方不管你的数据是从何而来的，我只是要从你仓库这里获取数据而已，而仓库则要自主分配如何更好更快地将数据提供给调用方。
 */
object NewsListRepository {
    private const val PAGE_SIZE = 10
    private val newsService = NewsService.create()

    fun getPagingData(): Flow<PagingData<SimpleNews>> {
        return Pager(
            config = PagingConfig(PAGE_SIZE),
            pagingSourceFactory = { NewsPagingSource(newsService) }).flow
    }
}