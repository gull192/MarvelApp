package gruzdev.artem.marvelapp.screens.selectPersonScreen.views.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import gruzdev.artem.marvelapp.R
import gruzdev.artem.marvelapp.screens.selectPersonScreen.model.HeroCard

@Composable
fun HeroItem (heroCard: HeroCard, modifier: Modifier = Modifier) {
    Box (modifier = modifier) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(heroCard.photoURL)
                .crossfade(true)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            placeholder = BitmapPainter(ImageBitmap.imageResource(R.drawable.placeholder)),
            modifier = Modifier
                .fillMaxSize()
                .clip(MaterialTheme.shapes.large)
        )
        Text(
            text = heroCard.title,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(16.dp),
            style = MaterialTheme.typography.h5,
            color = MaterialTheme.colors.onSecondary
        )
    }
}
