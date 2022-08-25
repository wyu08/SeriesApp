package cl.mario.seriesapp.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.core.text.HtmlCompat
import cl.mario.seriesapp.R
import cl.mario.seriesapp.data.models.Serie
import cl.mario.seriesapp.databinding.ActivityDetailBinding
import cl.mario.seriesapp.util.Constants.Companion.SHOW_ID
import coil.load
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {
    private val detailViewModel: DetailViewModel by viewModels()
    private lateinit var binding: ActivityDetailBinding
    private lateinit var serie: Serie
    private var id = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getIdFromIntent()
        getShowById()
        observeSerie()
        disableView()
    }

    private fun getIdFromIntent() {
        val intent = intent
        id = intent.getIntExtra(SHOW_ID, 0 )
    }

    private fun getShowById() {
        detailViewModel.getSerieById(id)
    }

    private fun observeSerie() {
        detailViewModel.observerSerie().observe(this){
            serie = it
            setView()
            enableView()
        }
    }

    private fun setView() {

        binding.mId.text = HtmlCompat.fromHtml("<b>" +  resources.getString(R.string.id) + "</b>" + " " + serie.id, HtmlCompat.FROM_HTML_MODE_LEGACY)

        binding.mRating.text = HtmlCompat.fromHtml("<b>" +  resources.getString(R.string.rating) + "</b>" + " " + serie.rating?.average, HtmlCompat.FROM_HTML_MODE_LEGACY)

        binding.mName.text = serie.name

        binding.mImage.load(serie.image?.original) {
            placeholder(R.color.purple_200)
            error(R.color.purple_200)
            crossfade(true)
            crossfade(400)
        }

        binding.mSummary.text = HtmlCompat.fromHtml(serie.summary!!, HtmlCompat.FROM_HTML_MODE_LEGACY)

        if (serie.network?.name == null) {
            binding.mNetwork.text = HtmlCompat.fromHtml("<b>" +  resources.getString(R.string.network) + "</b>" + " " + resources.getString(R.string.no_network), HtmlCompat.FROM_HTML_MODE_LEGACY)
        }else {
            binding.mNetwork.text = HtmlCompat.fromHtml("<b>" +  resources.getString(R.string.network) + "</b>" + " " + serie.network?.name, HtmlCompat.FROM_HTML_MODE_LEGACY)
        }

        if (serie.schedule?.time == null) {
            binding.mSchedule.text = HtmlCompat.fromHtml("<b>" +  resources.getString(R.string.schedule) + "</b>" + " " + serie.schedule?.days, HtmlCompat.FROM_HTML_MODE_LEGACY)
        }else {
            binding.mSchedule.text = HtmlCompat.fromHtml("<b>" +  resources.getString(R.string.schedule) + "</b>" + " " + serie.schedule?.days + " " + resources.getString(R.string.at) + " " + serie.schedule?.time, HtmlCompat.FROM_HTML_MODE_LEGACY)
        }

        if (serie.status == null) {
            binding.mStatus.text = HtmlCompat.fromHtml("<b>" +  resources.getString(R.string.status) + "</b>" + " " + resources.getString(R.string.no_status), HtmlCompat.FROM_HTML_MODE_LEGACY)
        }else {
            binding.mStatus.text = HtmlCompat.fromHtml("<b>" +  resources.getString(R.string.status) + "</b>" + " " + serie.status, HtmlCompat.FROM_HTML_MODE_LEGACY)
        }

        if (serie.type == null) {
            binding.mType.text = HtmlCompat.fromHtml("<b>" +  resources.getString(R.string.type) + "</b>" + " " + resources.getString(R.string.no_type), HtmlCompat.FROM_HTML_MODE_LEGACY)
        }else {
            binding.mType.text = HtmlCompat.fromHtml("<b>" +  resources.getString(R.string.type) + "</b>" + " " + serie.type, HtmlCompat.FROM_HTML_MODE_LEGACY)
        }

        when (serie.genres!!.size) {
            1 -> binding.mGenres.text = HtmlCompat.fromHtml("<b>" + resources.getString(R.string.genres) + "</b>" + " " + serie.genres!![0], HtmlCompat.FROM_HTML_MODE_LEGACY)
            2 -> binding.mGenres.text = HtmlCompat.fromHtml("<b>" + resources.getString(R.string.genres) + "</b>" + " " + serie.genres!![0] + " | " + serie.genres!![1], HtmlCompat.FROM_HTML_MODE_LEGACY)
            3 -> binding.mGenres.text = HtmlCompat.fromHtml("<b>" + resources.getString(R.string.genres) + "</b>" + " " + serie.genres!![0] + " | " + serie.genres!![1] + " | " + serie.genres!![2], HtmlCompat.FROM_HTML_MODE_LEGACY)
            4 -> binding.mGenres.text = HtmlCompat.fromHtml("<b>" + resources.getString(R.string.genres) + "</b>" + " " + serie.genres!![0] + " | " + serie.genres!![1] + " | " + serie.genres!![2] + " | " + serie.genres!![3], HtmlCompat.FROM_HTML_MODE_LEGACY)
            else -> binding.mGenres.text = HtmlCompat.fromHtml("<b>" + resources.getString(R.string.no_genres) + "</b>",HtmlCompat.FROM_HTML_MODE_LEGACY)
        }

        if (serie.officialSite == null) {
            binding.mOfficialSite.text = HtmlCompat.fromHtml("<b>" +  resources.getString(R.string.official_site) + "</b>" + " " + resources.getString(R.string.no_official_site), HtmlCompat.FROM_HTML_MODE_LEGACY)
        }else {
            binding.mOfficialSite.text = HtmlCompat.fromHtml("<b>" +  resources.getString(R.string.official_site) + "</b>" + " " + serie.officialSite, HtmlCompat.FROM_HTML_MODE_LEGACY)
        }
    }

    private fun enableView() {

        binding.mScrollView.visibility = View.VISIBLE
        binding.mProgressBar.visibility = View.GONE

    }

    private fun disableView() {

        binding.mScrollView.visibility = View.GONE
        binding.mProgressBar.visibility = View.VISIBLE

    }
}