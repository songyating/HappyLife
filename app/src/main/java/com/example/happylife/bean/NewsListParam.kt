package com.example.happylife.bean

/**
 * @author by syt
 * on 2021/8/13
 * desc:
 */
data class NewsListParam(
    val appId: String ,
    val projectId: String ,
    val appKey: String ,
    val modilarId:Long,
    val pageNo:Int,
    val pageSize:Int
)
