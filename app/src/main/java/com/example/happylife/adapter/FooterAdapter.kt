package com.example.happylife.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.happylife.databinding.LayoutFooterBinding

class FooterAdapter(val retry: () -> Unit) : LoadStateAdapter<FooterAdapter.ViewHolder>() {

    class ViewHolder(binding: LayoutFooterBinding) : RecyclerView.ViewHolder(binding.root) {
        val progressBar: ProgressBar = binding.progressBar
        val retryButton: Button = binding.btRetry
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): ViewHolder {
        val binding =
            LayoutFooterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        binding.btRetry.setOnClickListener {
            retry()
        }
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, loadState: LoadState) {
        holder.progressBar.isVisible = loadState is LoadState.Loading
        holder.retryButton.isVisible = loadState is LoadState.Error
    }

}

