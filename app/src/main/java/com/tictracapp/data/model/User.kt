package com.tictracapp.data.model

data class UserDetailsData(
    val id: Int,
    val profilePicture: String,
    val email: String,
    val name: String,
    val phone: String
)

data class UserListItemData(
    val id: Int,
    val name: String,
    val profilePicture: String
)