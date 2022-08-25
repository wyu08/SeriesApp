package cl.mario.seriesapp.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.mario.seriesapp.data.models.Series
import cl.mario.seriesapp.repo.SeriesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SeriesViewModel @Inject constructor(private val seriesRepository: SeriesRepository): ViewModel() {

    private val series = MutableLiveData<Series>()

    fun getSeries() = viewModelScope.launch {

        seriesRepository.getSeries().let {

            if (it.isSuccessful){
                series.value = it.body()
            }else{
                Log.e("Error", it.code().toString())
            }
        }
    }

    fun observerSeries(): LiveData<Series> {
        return series
    }


}