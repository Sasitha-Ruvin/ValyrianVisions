package com.example.valyrianvisions.data

import androidx.compose.ui.res.stringResource
import com.example.valyrianvisions.R
import com.example.valyrianvisions.model.Artists
import com.example.valyrianvisions.model.Paintings
import com.example.valyrianvisions.model.Pictures

class DataSource {

    fun loadPictures(): List<Pictures> {
        return listOf(
            Pictures(R.drawable.art2, R.string.featured_product1_name, 15.99),
            Pictures(R.drawable.art3, R.string.featured_product4_name, 18.99),
            Pictures(R.drawable.art4, R.string.featured_product3_name, 19.59),
            Pictures(R.drawable.art5, R.string.featured_product5_name, 15.99),
            Pictures(R.drawable.art6, R.string.featured_product6_name, 25.99),
            Pictures(R.drawable.art1, R.string.featured_product1_name, 12.99)
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

class PaintingsSource{
    fun loadPaintings():List<Paintings>{
        return listOf<Paintings>(
            Paintings(R.drawable.product1,R.string.painting_1,R.string.painting_1_description,20.25),
            Paintings(R.drawable.product2,R.string.painting_2,R.string.painting_2_description,15.25),
            Paintings(R.drawable.product3,R.string.painting_3,R.string.painting_3_description,15.35),
            Paintings(R.drawable.product4,R.string.painting_4,R.string.painting_4_description,20.25),
            Paintings(R.drawable.product5,R.string.painting_5,R.string.painting_5_description,20.99),
            Paintings(R.drawable.product6,R.string.painting_6,R.string.painting_6_description,21.95),

        )
    }
}

