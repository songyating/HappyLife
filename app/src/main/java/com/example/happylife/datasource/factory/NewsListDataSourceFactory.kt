package com.example.happylife.datasource.factory

import androidx.paging.DataSource
import com.example.happylife.datasource.NewsListDataSource
import com.example.happylife.bean.SimpleNews
import kotlinx.coroutines.CoroutineScope

/**
 * @author by syt
 * on 2021/8/16
 * desc:数据源工厂
 * DataSource一般由DataSource.Factory来初始化
 * create() 每次被调用都应该返回新对象，**不然调用invalidate() 时不仅不能刷新列表还会出现死循环。
 */
class NewsListDataSourceFactory(private val scope: CoroutineScope):
    DataSource.Factory<Int, SimpleNews>() {
    override fun create(): DataSource<Int, SimpleNews> {
        return NewsListDataSource(scope)
    }
}