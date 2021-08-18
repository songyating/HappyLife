package com.example.happylife.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.happylife.adapter.FooterAdapter
import com.example.happylife.adapter.NewsAdapter
import com.example.happylife.databinding.FragmentNewsListBinding
import com.example.happylife.viewModel.NewsListViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass.
 * Use the [NewsListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NewsListFragment : Fragment() {
    lateinit var binding: FragmentNewsListBinding
    lateinit var layoutManager: LinearLayoutManager
    lateinit var adapter: NewsAdapter
    private val viewModel by lazy { ViewModelProvider(this).get(NewsListViewModel::class.java) }

    //companion object 修饰为伴生对象,伴生对象在类中只能存在一个，
    companion object {
        fun getInstance(key: String): NewsListFragment {
            val fragment = NewsListFragment()
            Bundle().also {
                it.putString("QueryKey", key)
                fragment.arguments = it
            }
            return fragment
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            FragmentNewsListBinding.inflate(LayoutInflater.from(requireContext()), container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.layoutManager = layoutManager

        adapter = NewsAdapter()
        binding.recyclerView.adapter =
            adapter.withLoadStateFooter(FooterAdapter { adapter.retry() })

        lifecycleScope.launch {
            viewModel.flow.collectLatest { pagingData ->
                adapter.submitData(pagingData)
            }
        }

        binding.refreshLayout.setOnRefreshListener {
            adapter.refresh()
        }

        adapter.addLoadStateListener {
            when (it.refresh) {
                is LoadState.NotLoading -> {
                    binding.refreshLayout.isRefreshing = false
                }
            }
        }


    }
}