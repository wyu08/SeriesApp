package cl.mario.seriesapp.repo

import cl.mario.seriesapp.data.remote.SeriesApi
import javax.inject.Inject

class SeriesRepository @Inject constructor(private val seriesApi: SeriesApi){

    suspend fun getSeries() = seriesApi.getSeries()
}