package com.example.happylife.datasource

import androidx.paging.PageKeyedDataSource
import androidx.paging.PagingSource
import com.example.happylife.bean.SimpleNews
import com.example.happylife.repository.NewsListRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * @author by syt
 * on 2021/8/16
 * desc:数据源
 * 协程作用域scope
 * PageKeyedDataSource: 通过当前页相关的key来获取数据，非常常见的是key作为请求的page的大小。
 * ItemKeyedDataSource: 通过具体item数据作为key，来获取下一页数据。例如聊天会话，请求下一页数据可能需要上一条数据的id。
 * PositionalDataSource: 通过在数据中的position作为key，来获取下一页数据。这个典型的就是上面所说的在Database中的运用。
 */
class NewsListDataSource(private val scope: CoroutineScope) :
    PagingSource<Int, SimpleNews>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SimpleNews> {
        TODO("Not yet implemented")

    }


//    /**
//     * 最开始加载的时候调用的数据请求方法
//     */
//    override fun loadInitial(
//        params: LoadInitialParams<Int>,
//        callback: LoadInitialCallback<Int, SimpleNews>
//    ) {
//        scope.launch {
//            val response = NewsListRepository.getNewsList(
//                "110083", "4", "xy001",
//                126247, 1, params.requestedLoadSize
//            )
//            callback.onResult(response.Data.Contents, null, 2)
//        }
//    }
//
//    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, SimpleNews>) {
//        scope.launch {
//            val response = NewsListRepository.getNewsList(
//                "110083", "4", "xy001",
//                126247, params.key, params.requestedLoadSize
//            )
//            callback.onResult(response.Data.Contents, params.key + 1)
//        }
//
//    }
//
//    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, SimpleNews>) {
//    }
}