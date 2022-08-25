package cl.mario.seriesapp.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cl.mario.seriesapp.R
import cl.mario.seriesapp.data.models.Serie
import cl.mario.seriesapp.databinding.ItemSerieBinding
import coil.load

class SerieAdapter: RecyclerView.Adapter<SerieAdapter.MiSerieViewHolder>() {

    private var series: List<Serie> = ArrayList()
    private lateinit var onCardViewClick: OnCardViewClick

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MiSerieViewHolder {
        return MiSerieViewHolder(ItemSerieBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: MiSerieViewHolder, position: Int) {
        val serie = series[position]

        holder.binding.apply {

            mImagen.load(serie.image?.original){
                placeholder(R.color.purple_200)
                error(R.color.purple_200)
                crossfade(true)
                crossfade(400)
            }

            mTitulo.text = serie.name
        }

        holder.binding.mCardView.setOnClickListener{
            onCardViewClick.onClick(serie)
        }
    }

    override fun getItemCount(): Int {
        return series.size
    }

    class MiSerieViewHolder(val binding: ItemSerieBinding): RecyclerView.ViewHolder(binding.root)

    fun setSeries(series: List<Serie>) {
        this.series = series
    }

    interface OnCardViewClick {
        fun onClick(serie: Serie)
    }

    fun setOnCardViewClick(onCardViewClick: OnCardViewClick) {
        this.onCardViewClick = onCardViewClick
    }

}