package Interfaces

import Beans.Path
import Beans.Prim
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PlaceHolder {
    // parameters: src and dest /obtain-minimum-path?src=3&dest=4
    //GET /obtain-minimum-path
    // parameters: src and dest
    @GET("obtain-minimum-path")
    fun obtainMinimumPath(
        @Query("src") src: String,
        @Query("dest") dest: String
    ): Call<List<List<String>>>

    @GET("airlines")
    fun obtainAirlines(
        @Query("country") country: String? = null,
        @Query("limit") limit: String? = null,
        @Query("destinations") destinations: String? = null
    ): Call<List<String>>

    @GET("prim")
    fun obtainLowestCost(
        @Query("src") src: String,
        @Query("dest") dest: String
    ): Call<Prim>
}