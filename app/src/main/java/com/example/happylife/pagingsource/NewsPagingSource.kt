package com.example.happylife.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.happylife.api.NewsService
import com.example.happylife.bean.SimpleNews
import java.lang.Exception

/**
 * @author by syt
 * on 2021/8/16
 * desc:请求数据
 * newsService后端服务实例
 * PagingSource<页数，每一项数据类型>
 */
class NewsPagingSource(private val newsService: NewsService) :
    PagingSource<Int, SimpleNews>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SimpleNews> {
        return try {
            //页码
            val pageNo = params.key ?: 1
            //每一页包含多少条数据
            val pageSize = params.loadSize
            //从响应数据解析出来的稿件列表
            val newsListResponse =
                newsService.getNewsList("110083", "4", "xy001", 126247, pageNo, pageSize)
            val newsListData = newsListResponse.Data.Contents
            //上一页 如果当前页已经是第一页或最后一页，那么它的上一页或下一页就为null
            val prevKey = if (pageNo > 1) pageNo - 1 else null
            //下一页
            val nextKey = if (newsListData.isNotEmpty()) pageNo + 1 else null
            //加载成功
            LoadResult.Page(newsListData, prevKey, nextKey)
        } catch (e: Exception) {
            //加载失败
            LoadResult.Error(e)
        }
    }

}