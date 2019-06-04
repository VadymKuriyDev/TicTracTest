package com.tictracapp.api.response

class UsersApiResponse constructor(val arrivals: List<UserApiEntity>)

class UserApiEntity constructor(val profile_picture: String,
    val email:String,
    val name:String,
    val infos:String)
