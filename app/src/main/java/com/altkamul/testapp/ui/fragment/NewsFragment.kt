package com.altkamul.testapp.ui.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.altkamul.testapp.R
import com.altkamul.testapp.ui.adapter.NewsAdapter
import com.altkamul.testapp.ui.viewModel.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_news.*

@AndroidEntryPoint
class NewsFragment : Fragment(R.layout.fragment_news) {


    lateinit var newsAdapter: NewsAdapter

    private val viewModel: NewsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecyclerView()
        observeLiveData()
        newsAdapter.setOnItemClickListener {
//            val bundle = Bundle().apply {
////                putSerializable("article", it)
//            }
//            findNavController().navigate(
//                    R.id.breakingNewsFragment,
//                    bundle
//            )
        }
        refreshNews()
    }

    private fun observeLiveData() {
        viewModel.apply {
            newsLiveData.observe(viewLifecycleOwner, Observer {
                newsAdapter.differ.submitList(it)
            })
        }
    }

    private fun setUpRecyclerView() {
        newsAdapter = NewsAdapter()
        rvSavedNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

    private fun refreshNews() {
        refreshLayout.setOnRefreshListener {
            viewModel.getAllNews()
            Toast.makeText(activity, "Updated", Toast.LENGTH_SHORT).show()
            refreshLayout.isRefreshing = false
        }
    }
    }



