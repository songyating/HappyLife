package com.example.happylife.repository

import com.example.happylife.api.NewsService
import com.example.happylife.bean.NewsListResponse

/**
 * @author by syt
 * on 2021/8/16
 * desc:
 */
object NewsListRepository {
    suspend fun getNewsList(
        appId: String,
        projectId: String,
        appKey: String,
        modilarId: Long,
        pageNo: Int,
        pageSize: Int
    ): NewsListResponse {
       return NewsService.create().getNewsList(appId, projectId, appKey, modilarId, pageNo, pageSize)
    }
}