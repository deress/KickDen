package com.dicoding.kikcden.favorite.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.kickden.core.domain.model.Sneakers
import com.dicoding.kickden.core.ui.SneakersAdapter
import com.dicoding.kickden.databinding.FragmentFavoriteBinding
import com.dicoding.kickden.detail.DetailActivity
import com.dicoding.kickden.di.FavoriteModuleDependencies
import com.dicoding.kikcden.favorite.di.DaggerFavoriteComponent
import com.dicoding.kikcden.favorite.di.ViewModelFactory
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

//@AndroidEntryPoint
class FavoriteFragment : Fragment() {
    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!
    private val sneakersAdapter: SneakersAdapter by lazy { SneakersAdapter(::sneakerItemClicked) }

    @Inject
    lateinit var factory: ViewModelFactory
    private val viewModel: FavoriteViewModel by viewModels{ factory }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        DaggerFavoriteComponent.builder()
            .context(requireContext())
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    requireContext(),
                    FavoriteModuleDependencies::class.java
                )
            )
            .build()
            .inject(this)

        super.onViewCreated(view, savedInstanceState)



        val layoutManager = GridLayoutManager(requireActivity(),2, RecyclerView.VERTICAL, false)
        binding.rvSneakers.layoutManager = layoutManager

        viewModel.listSneakers.observe(viewLifecycleOwner) { result ->
            showListSneakers(result)
            if (result.isNotEmpty()) showLoading(false) else showLoading(true)
        }
    }


    private fun showListSneakers(items: List<Sneakers>) {
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
        super.onDestroyView()
        _binding = null
    }

}