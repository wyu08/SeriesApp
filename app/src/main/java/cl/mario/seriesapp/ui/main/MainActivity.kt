package cl.mario.seriesapp.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import cl.mario.seriesapp.data.models.Serie
import cl.mario.seriesapp.databinding.ActivityMainBinding
import cl.mario.seriesapp.ui.detail.DetailActivity
import cl.mario.seriesapp.util.Constants.Companion.SHOW_ID
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val seriesViewModel: SeriesViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private lateinit var serieAdapter: SerieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        serieAdapter = SerieAdapter()

        setUpRecyclerView()
        getSeries()
        observeSeries()
        setOnCardViewClick()
        disableViews()

    }

    private fun setUpRecyclerView() {

        binding.mRecyclerView.apply {
            adapter = serieAdapter
        }
    }

    private fun getSeries() {
        seriesViewModel.getSeries()
    }

    private fun observeSeries() {
        seriesViewModel.observerSeries().observe(this){
            serieAdapter.setSeries(it)
            enableView()
        }
    }

    private fun setOnCardViewClick(){

        serieAdapter.setOnCardViewClick(object : SerieAdapter.OnCardViewClick {
            override fun onClick(serie: Serie) {

                val intent = Intent(this@MainActivity, DetailActivity::class.java)
                intent.putExtra(SHOW_ID, serie.id)
                startActivity(intent)
            }
        })
    }

    private fun enableView() {
        binding.mRecyclerView.visibility = View.VISIBLE
        binding.mProgressBar.visibility = View.GONE
    }

    private fun disableViews(){

        binding.mRecyclerView.visibility = View.GONE
        binding.mProgressBar.visibility = View.VISIBLE
    }
}