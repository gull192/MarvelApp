package gruzdev.artem.marvelapp.screens.select_person_screen.components

import android.graphics.drawable.PaintDrawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import gruzdev.artem.marvelapp.screens.select_person_screen.model.HeroCard
import gruzdev.artem.marvelapp.ui.theme.Shapes
import gruzdev.artem.marvelapp.ui.theme.Typography

@Composable
fun HeroItem (heroCard: HeroCard, modifier: Modifier = Modifier) {
    Box (modifier = modifier) {
        Image(painter = painterResource(heroCard.image),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .clip(Shapes.large),
            contentScale = ContentScale.Crop
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