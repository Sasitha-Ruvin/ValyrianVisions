package com.example.valyrianvisions.Screens

import android.content.res.Configuration
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.valyrianvisions.Authentications.AuthState
import com.example.valyrianvisions.Authentications.AuthViewModel
import com.example.valyrianvisions.R
import com.example.valyrianvisions.CommonComps.ScreenWithTopBarAndBottomNav
import com.example.valyrianvisions.data.ArtistSource
import com.example.valyrianvisions.data.DataSource
import com.example.valyrianvisions.model.Artists
import com.example.valyrianvisions.model.Pictures
import kotlinx.coroutines.delay


//Home Screen
@Composable
fun HomeScreen(modifier: Modifier = Modifier, navController: NavController, authViewModel: AuthViewModel) {
    var search by remember { mutableStateOf("") }
    val authState = authViewModel.authstate.observeAsState()
    var startAnimation by remember{ mutableStateOf(false) }


    LaunchedEffect(Unit) {
        startAnimation = true
    }

    val offsetX by animateDpAsState(
        targetValue = if(startAnimation) 0.dp else 3000.dp,
        animationSpec = tween(durationMillis = 400)
    )

    LaunchedEffect(authState.value) {
        when (authState.value) {
            is AuthState.Unauthenticated -> navController.navigate("login")
            else -> Unit
        }
    }
    ScreenWithTopBarAndBottomNav(navController = navController) { innerPadding->
        Box(modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .offset(x = offsetX)
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
              Spacer(modifier = Modifier.height(5.dp))

                // Main content
                Column(
                    modifier = Modifier
                        .weight(2f)
                        .verticalScroll(rememberScrollState())
                        .fillMaxWidth()
                        .padding(bottom = 60.dp)
                ) {
                    SearchBar(search = search, onSearchChange = { search = it })
                    Spacer(modifier = Modifier.height(15.dp))
                    Slideshow()
                    Spacer(modifier = Modifier.height(16.dp))
                    FeaturedText()
                    Spacer(modifier = Modifier.height(10.dp))
                    FeaturedProductList(pictureList = DataSource().loadPictures(), navController = navController)
                    Spacer(modifier = Modifier.height(25.dp))
                    ImageWithOverlay()
                    Spacer(modifier = Modifier.height(10.dp))
                    FeaturedArtistText()
                    Spacer(modifier = Modifier.height(10.dp))
                    FeaturedArtistList(artistList = ArtistSource().loadArtists())

                    Column(modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally)
                    {
                        TextButton(onClick = {authViewModel.signOut()}) {
                            Text(text = "Sign Out")

                        }

                    }
                }
            }
        }


}


}


//Search Bar
@Composable
fun SearchBar(search: String, onSearchChange: (String) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .background(MaterialTheme.colorScheme.secondaryContainer, RoundedCornerShape(60.dp))
            .clip(RoundedCornerShape(60.dp))
            .height(56.dp),
        contentAlignment = Alignment.Center
    ) {
        TextField(
            value = search,
            onValueChange = onSearchChange,
            placeholder = { Text("Search") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent
            ),
            singleLine = true,
            trailingIcon = { Icon(Icons.Default.Search, contentDescription = null) }
        )
    }
}

//Main Image Overlay
@Composable
fun ImageWithOverlay() {
    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        // Background Image
        Image(
            painter = painterResource(R.drawable.art1),
            contentDescription = "Art One",
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp),
            contentScale = ContentScale.Crop
        )

        // Overlaying Text
        Column(
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.Center)
        ) {
            Text(
                text = stringResource(R.string.immerse_in_art),
                style = MaterialTheme.typography.headlineMedium.copy(color = Color.White, fontWeight = FontWeight.Bold)
            )
        }
    }
}

//Featured Product section Text
@Composable
fun FeaturedText() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Featured",
            fontSize = 20.sp,
            style = MaterialTheme.typography.bodyMedium.copy(
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.Bold
            )
        )
        Spacer(modifier = Modifier.weight(1f))
        TextButton(
            onClick = { /*TODO*/ },
            modifier = Modifier.align(Alignment.CenterVertically)
        ) {
            Text(
                text = "Show more",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color.Gray,
                    fontWeight = FontWeight.Bold
                ),
                fontSize = 15.sp
            )
        }
    }
}




//Featured Product Card
@Composable
fun FeaturedProductCard(picture:Pictures, modifier: Modifier = Modifier,navController: NavController){
    Card(modifier = Modifier
        .padding(horizontal = 12.dp)
        .width(200.dp)
        .clickable { navController.navigate("detailedProductView/${picture.imageResourceId}") }
        .height(280.dp)) {
        Column {
            Image(painter = painterResource(picture.imageResourceId), contentDescription = stringResource(
                R.string.product_image
            ),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(130.dp)
                    .width(200.dp)
            )
            Text(text = stringResource(id = picture.stringResourceId),
                fontSize = 18.sp,
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            )
            Text(text = stringResource(picture.priceResourceId),
                fontSize = 18.sp,
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                )
            Button(onClick = { /*TODO*/ },
                modifier = Modifier.padding(8.dp)
            ) {
                Text(text = "Buy")
            }
        }

    }


}

//Featured Products List
@Composable
fun FeaturedProductList(pictureList: List<Pictures>, navController: NavController, modifier: Modifier = Modifier) {
    LazyRow(modifier = Modifier) {
        items(pictureList) { picture ->
            FeaturedProductCard(picture = picture, modifier = Modifier.padding(10.dp), navController = navController)
        }
    }
}



//Slideshow Composable
@Composable
fun Slideshow(modifier: Modifier = Modifier, slideInterval: Long = 5000) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val isPortrait = configuration.orientation == Configuration.ORIENTATION_PORTRAIT
    val imageHeight = if (isPortrait) 200.dp else 350.dp

    val imageResources = listOf(
        R.drawable.slide1,
        R.drawable.slideshow2,
        R.drawable.slideshow3
    )

    var currentIndex by remember { mutableStateOf(0) }

    // Update the current index after every slideInterval duration
    LaunchedEffect(currentIndex) {
        while (true) {
            delay(slideInterval)
            currentIndex = (currentIndex + 1) % imageResources.size
        }
    }

    // Crossfade for smooth transitions
    Crossfade(targetState = currentIndex, modifier = Modifier.fillMaxWidth()) { index ->
        Image(
            painter = painterResource(id = imageResources[index]),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(imageHeight)
        )
    }
}

@Composable
fun FeaturedArtistText(){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(R.string.featured_artists),
            fontSize = 20.sp,
            style = MaterialTheme.typography.bodyMedium.copy(
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.Bold
            )
        )
    }

}

@Composable
fun FeaturedArtistCard(artists: Artists, modifier: Modifier = Modifier){
    Card(modifier = Modifier
        .padding(horizontal = 12.dp)
        .width(100.dp)
        .height(100.dp)
        .clip(CircleShape)
    )
    {
        Column {
        Image(painter = painterResource(artists.imageResourceId), contentDescription = null,
            contentScale = ContentScale.Crop, modifier = Modifier
                .height(100.dp)
                .width(100.dp)
                .clip(
                    CircleShape
                ))
        }
    }
}


@Composable
fun FeaturedArtistList(artistList: List<Artists>, modifier: Modifier = Modifier){
    LazyRow(modifier = Modifier){
        items(artistList){artist->
            FeaturedArtistCard(artists = artist, modifier = Modifier.padding((10.dp)))
        }
    }
}

//Category Text
@Composable
fun CategoriesText(){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(R.string.categories),
            fontSize = 20.sp,
            style = MaterialTheme.typography.bodyMedium.copy(
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.Bold
            )
        )
    }
}

//Catergory Buttons
@Composable
fun CategotyButtons(){

}
