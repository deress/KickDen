package com.dicoding.kickden.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.kickden.core.data.Resource
import com.dicoding.kickden.core.domain.model.Sneakers
import com.dicoding.kickden.core.ui.SneakersAdapter
import com.dicoding.kickden.databinding.FragmentHomeBinding
import com.dicoding.kickden.detail.DetailActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()
    private val sneakersAdapter: SneakersAdapter by lazy { SneakersAdapter(::sneakerItemClicked) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = GridLayoutManager(requireActivity(),2, RecyclerView.VERTICAL, false)
        binding.rvSneakers.layoutManager = layoutManager

        setupSearch()

        viewModel.getSneakers().observe(viewLifecycleOwner) { result ->
            when (result) {
                is Resource.Loading -> {
                    showLoading(true)
                }
                is Resource.Success -> {
                    showListSneakers(result.data)
                    showLoading(false)
                }
                is Resource.Error -> {
                    showLoading(false)
                }
                else -> {}
            }
        }
    }

    private fun setupSearch() {
        with(binding) {
            searchView.setupWithSearchBar(searchBar)
            searchView
                .editText
                .setOnEditorActionListener { textView, actionId, event ->
                    searchBar.setText(searchView.text)
                    searchView.hide()
                    if (searchView.text.toString() == "") {
                        viewModel.search("")
                        false
                    } else {
                        val keyword = searchView.text.toString()
                        viewModel.search(keyword)
                        false
                    }
                }
        }
    }

    private fun showListSneakers(items: List<Sneakers>?) {
        sneakersAdapter.submitList(items)
        binding.rvSneakers.adapter = sneakersAdapter
    }

    private fun showLoading(isLoading:Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun sneakerItemClicked(sneakers: Sneakers) {
        val intentDetail = Intent(context, DetailActivity::class.java)
        intentDetail.putExtra(DetailActivity.EXTRA_DATA, sneakers)
        startActivity(intentDetail)
    }

    override fun onDestroyView() {
//        binding.rvSneakers.adapter = null
        super.onDestroyView()
        _binding = null
    }
}