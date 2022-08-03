package com.lira.footballnews.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.lira.footballnews.databinding.FragmentFavoritesBinding
import com.lira.footballnews.ui.adapter.NewsAdapter
import kotlinx.coroutines.launch

class FavoritesFragment : Fragment() {

    private lateinit var favoritesViewModel: FavoritesViewModel
    private var _binding: FragmentFavoritesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        favoritesViewModel = ViewModelProvider(this).get(FavoritesViewModel::class.java)

        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        loadFavoriteNews()

        return root
    }

    private fun loadFavoriteNews() {
        lifecycleScope.launch {
            favoritesViewModel.loadFavoriteNews().observe(viewLifecycleOwner, Observer { favoriteNews ->
                    binding.rvNews.layoutManager = LinearLayoutManager(context)
                    binding.rvNews.adapter = NewsAdapter(favoriteNews) { news ->
                        lifecycleScope.launch {
                            favoritesViewModel.saveNews(news)
                        }
                        loadFavoriteNews()
                    }
                })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}