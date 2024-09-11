package com.example.valyrianvisions.ViewModels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.valyrianvisions.model.User
import kotlinx.coroutines.launch

class UserProfileViewModel:ViewModel()
{
    private val userProfileData = mutableStateOf(
        User(
            username = "johnD",
            password = "*********",
            email = "john@mail.com",
            contact = "123-456-7890"
        )
    )

    val userProfile = userProfileData

    fun updateUserData(username:String, password:String, email:String, contact:String){
        viewModelScope.launch {
            userProfileData.value = User(
                username = username,
                password = password,
                email = email,
                contact = contact
            )
        }
    }
}