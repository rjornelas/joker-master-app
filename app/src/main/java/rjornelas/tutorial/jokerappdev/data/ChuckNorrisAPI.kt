package rjornelas.tutorial.jokerappdev.data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import rjornelas.tutorial.jokerappdev.model.Joke

interface ChuckNorrisAPI {

    @GET("jokes/categories")
    fun findAllCategories(@Query("apiKey") apiKey: String) : Call<List<String>>

    @GET("jokes/random")
    fun findJoke(@Query("category") categoryName: String? = null, @Query("apiKey") apiKey: String = HTTPClient.API_KEY) : Call<Joke>
}