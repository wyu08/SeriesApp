package cl.mario.seriesapp.data.remote

import cl.mario.seriesapp.data.models.Series
import cl.mario.seriesapp.util.Constants.Companion.SHOWS
import retrofit2.Response
import retrofit2.http.GET

interface SeriesApi {

    @GET(SHOWS)
    suspend fun getSeries(): Response<Series>
}