package com.example.valyrianvisions.ViewModels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.valyrianvisions.model.WishlistItem

class WishListViewModel(private val savedStateHandle: SavedStateHandle):ViewModel()
{
    private val _wishlistItem = mutableListOf<WishlistItem>()
    val Items : List<WishlistItem> get () = _wishlistItem

    fun addItemToWish(wishlistItem: WishlistItem){
        _wishlistItem.add(wishlistItem)
    }
    fun clearWish(){
        _wishlistItem.clear()
    }
}