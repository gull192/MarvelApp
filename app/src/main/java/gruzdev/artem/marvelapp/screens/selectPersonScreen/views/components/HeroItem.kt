package gruzdev.artem.marvelapp.screens.selectPersonScreen.views.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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
import gruzdev.artem.marvelapp.ui.theme.Shapes
import gruzdev.artem.marvelapp.ui.theme.Typography

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
                .clip(Shapes.large)
        )
        Text(
            text = heroCard.title,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(16.dp),
            style = Typography.h5,
            color = Color.White
        )
    }
}
