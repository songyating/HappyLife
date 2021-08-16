package com.example.happylife.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.happylife.fragment.NewsListFragment

/**
 * @author by syt
 * on 2021/8/13
 * desc:
 */
class NewsListPageAdapter(fragment: Fragment, private val items: Array<String>) :
    FragmentStateAdapter(fragment) {

    // 1.
    override fun getItemCount(): Int {
        return items.size
    }

    // 2
    override fun createFragment(position: Int): Fragment {
        return NewsListFragment.getInstance("")
    }

}

