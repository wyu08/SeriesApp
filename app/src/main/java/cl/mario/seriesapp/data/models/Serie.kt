package cl.mario.seriesapp.data.models

data class Serie(
    val id: Int,
    val _links: Links?,
    val averageRuntime: Int?,
    val dvdCountry: DvdCountry?,
    val ended: String?,
    val externals: Externals?,
    val genres: List<String>?,
    val image: Image?,
    val language: String?,
    val name: String?,
    val network: Network?,
    val officialSite: String?,
    val premiered: String?,
    val rating: Rating?,
    val runtime: Int?,
    val schedule: Schedule?,
    val status: String?,
    val summary: String?,
    val type: String?,
    val updated: Int?,
    val url: String?,
    val webChannel: WebChannel?,
    val weight: Int?
)