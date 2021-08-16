package com.example.happylife.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.happylife.adapter.NewsAdapter
import com.example.happylife.databinding.FragmentNewsListBinding
import com.example.happylife.viewModel.NewsListViewModel
import kotlinx.coroutines.*

/**
 * A simple [Fragment] subclass.
 * Use the [NewsListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NewsListFragment : Fragment() {
    lateinit var binding: FragmentNewsListBinding
    lateinit var layoutManager: LinearLayoutManager
    lateinit var adapter: NewsAdapter
    lateinit var viewModel: NewsListViewModel

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
        binding.recyclerView.adapter = adapter

        viewModel = ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                if (modelClass.isAssignableFrom(NewsListViewModel::class.java)) {
                    return NewsListViewModel() as T
                }
                throw  IllegalArgumentException(" unKnown ViewModel class ")
            }
        }).get(NewsListViewModel::class.java)

        viewModel.pagedListLiveData.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
            binding.refreshLayout.isRefreshing = false
        })

        binding.refreshLayout.setOnRefreshListener {
            viewModel.resetQuery()
        }
    }
}