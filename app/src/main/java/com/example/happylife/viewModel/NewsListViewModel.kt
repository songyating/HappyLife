package com.example.happylife.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.happylife.repository.NewsListRepository

/**
 * @author by syt
 * on 2021/8/16
 * desc:
 */
class NewsListViewModel : ViewModel() {
    //cachedIn将服务器返回的数据在viewModelScope这个作用域内进行缓存
    //手机横竖屏发生了旋转导致Activity重新创建，Paging 3就可以直接读取缓存中的数据，而不用重新发起网络请求了。
    val flow = NewsListRepository.getPagingData().cachedIn(viewModelScope)
}