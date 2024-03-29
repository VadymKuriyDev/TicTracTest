package com.tictracapp.api

import com.tictracapp.api.response.UserApiEntity
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Url

interface CommonApiInterface {

    @GET
    fun getUsersList(@Url documentUrl: String): Single<List<UserApiEntity>>
}