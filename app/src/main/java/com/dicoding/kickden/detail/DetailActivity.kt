package com.dicoding.kickden.detail

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.dicoding.kickden.R
import com.dicoding.kickden.core.domain.model.Sneakers
import com.dicoding.kickden.databinding.ActivityDetailBinding
import com.google.android.material.appbar.MaterialToolbar
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    private val viewModel: DetailViewModel by viewModels()

    private lateinit var binding: ActivityDetailBinding
    private var isFavorited = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)



        val toolbar: MaterialToolbar = binding.topAppBar
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            title = "Sneakers Detail"
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.baseline_arrow_back_ios_new_24)
        }

        val sneaker = intent.getParcelableExtra<Sneakers>(EXTRA_DATA)
        showDetailSneaker(sneaker)
    }

    private fun showDetailSneaker(detailSneaker : Sneakers?) {
        detailSneaker?.let {
            binding.tvSneakerBrand.text = detailSneaker.brand
            binding.tvSneakerName.text = detailSneaker.name
            binding.tvSneakerColor.text = detailSneaker.color
            binding.tvSneakerPrice.text = resources.getString(R.string.sneaker_price, detailSneaker.price)
            Glide.with(this@DetailActivity)
                .load(detailSneaker.photoUrl)
                .into(binding.ivDetailImage)


            isFavorited = detailSneaker.isFavorited == true

            if (isFavorited) {
                binding.ivFavorite.setImageDrawable(ContextCompat.getDrawable(this@DetailActivity, R.drawable.baseline_favorite_24))
            } else {
                binding.ivFavorite.setImageDrawable(ContextCompat.getDrawable(this@DetailActivity, R.drawable.baseline_favorite_border_24))
            }

            binding.ivFavorite.setOnClickListener {
                setStatusFavorite(detailSneaker)
            }
        }
    }

    private fun setStatusFavorite(sneaker: Sneakers) {
        isFavorited = !isFavorited
        if (isFavorited) {
            viewModel.addFavorite(sneaker, isFavorited)
            binding.ivFavorite.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.baseline_favorite_24))
        } else {
            viewModel.removeFavorite(sneaker, isFavorited)
            binding.ivFavorite.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.baseline_favorite_border_24))
        }
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
    }
}