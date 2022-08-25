package cl.mario.seriesapp.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.mario.seriesapp.data.models.Serie
import cl.mario.seriesapp.repo.SeriesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val seriesRepository: SeriesRepository): ViewModel(){

    private val serie = MutableLiveData<Serie>()

    fun getSerieById(id: Int) = viewModelScope.launch {

        seriesRepository.getSeries().let {
            if (it.isSuccessful) {

                for (serieResponse in it.body()!!){

                    if(serieResponse.id == id) {
                        serie.value = serieResponse
                    }
                }

            } else {
                Log.e("Error", it.code().toString())
            }
        }
    }

    fun observerSerie(): LiveData<Serie> {
        return serie
    }

}