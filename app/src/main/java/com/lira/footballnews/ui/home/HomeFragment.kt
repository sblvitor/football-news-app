package com.lira.footballnews.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.lira.footballnews.databinding.FragmentHomeBinding
import com.lira.footballnews.ui.adapter.NewsAdapter
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root


        binding.rvNews.layoutManager = LinearLayoutManager(context)

        observeNews()
        //observeStates()

        binding.srlNews.setOnRefreshListener(this::observeNews)

        return root
    }

    private fun observeNews() {
        homeViewModel.news.observe(viewLifecycleOwner, {
            binding.rvNews.adapter = NewsAdapter(it) { news ->
                lifecycleScope.launch {
                    homeViewModel.saveNews(news)
                }
            }
        })
    }

    /*private fun observeStates(){
        homeViewModel.states.observe(viewLifecycleOwner, { state ->
            when(state){
                HomeViewModel.State.DOING -> {
                    binding.srlNews.isRefreshing = true
                }
                HomeViewModel.State.DONE -> {
                    binding.srlNews.isRefreshing = false
                }
                HomeViewModel.State.ERROR -> {
                    binding.srlNews.isRefreshing = false
                }
            }
        })
    }*/

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}