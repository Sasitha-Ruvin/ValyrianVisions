package com.example.valyrianvisions.data

import com.example.valyrianvisions.R
import com.example.valyrianvisions.model.Artists
import com.example.valyrianvisions.model.Pictures

class DataSource {
    fun loadPictures(): List<Pictures> {
        return listOf<Pictures>(
            Pictures(R.drawable.art2, R.string.featured_product1_name, R.string.product1_price),
            Pictures(R.drawable.art3, R.string.featured_product4_name, R.string.product1_price),
            Pictures(R.drawable.art4, R.string.featured_product3_name, R.string.product3_price),
            Pictures(R.drawable.art5, R.string.featured_product5_name, R.string.product5_price),
            Pictures(R.drawable.art6, R.string.featured_product6_name, R.string.product6_price),
            Pictures(R.drawable.art1, R.string.featured_product1_name, R.string.product1_price),

        )
    }
}

class ArtistSource{
    fun loadArtists():List<Artists>{
        return listOf<Artists>(
        Artists(R.drawable.artist1,R.string.artist_1),
        Artists(R.drawable.artist3,R.string.artist_2),
        Artists(R.drawable.artist2,R.string.artist_3),
        Artists(R.drawable.artist4,R.string.artist_4),
        Artists(R.drawable.artist6,R.string.artist_5),
        Artists(R.drawable.artist5,R.string.artist_6),
        )
    }
}