package com.lira.footballnews.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.lira.footballnews.databinding.FragmentHomeBinding
import com.lira.footballnews.ui.adapter.NewsAdapter
import kotlinx.coroutines.launch
import java.util.*

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    private var adapter: NewsAdapter = NewsAdapter(Collections.emptyList()) {}

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root


        binding.rvNews.layoutManager = LinearLayoutManager(context)
        binding.rvNews.adapter = adapter

        observeNews()
        observeStates()

        binding.srlNews.setOnRefreshListener(homeViewModel::findNews)

        return root
    }

    private fun observeNews() {
        homeViewModel.news.observe(viewLifecycleOwner, Observer {
            adapter = NewsAdapter(it) { news ->
                lifecycleScope.launch {
                    homeViewModel.saveNews(news)
                }
            }
            binding.rvNews.adapter = adapter
        })
    }

    private fun observeStates(){
        homeViewModel.states.observe(viewLifecycleOwner, Observer { state ->
            when(state){
                HomeViewModel.State.DOING -> binding.srlNews.isRefreshing = true
                HomeViewModel.State.DONE -> binding.srlNews.isRefreshing = false
                HomeViewModel.State.ERROR -> {
                    binding.srlNews.isRefreshing = false
                    Snackbar.make(binding.srlNews, "Network Error.", Snackbar.LENGTH_SHORT).show()
                }
                else -> {
                    binding.srlNews.isRefreshing = false
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}