@file:Suppress("OPT_IN_IS_NOT_ENABLED")

package gruzdev.artem.marvelapp.screens.select_person_screen.components

import android.util.Log
import androidx.compose.animation.animateContentSize

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.util.lerp
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import dev.chrisbanes.snapper.ExperimentalSnapperApi
import dev.chrisbanes.snapper.rememberSnapperFlingBehavior
import gruzdev.artem.marvelapp.screens.select_person_screen.model.HeroCard
import dev.chrisbanes.snapper.LazyListSnapperLayoutInfo
import dev.chrisbanes.snapper.rememberLazyListSnapperLayoutInfo
import kotlin.math.abs

@OptIn(ExperimentalSnapperApi::class)
@Composable
fun RowHero(heros: List<HeroCard>, changeCurrentItem: (Int) -> Unit) {
    val lazyListState = rememberLazyListState()
    val layoutInfo: LazyListSnapperLayoutInfo = rememberLazyListSnapperLayoutInfo(lazyListState)
    val  maxItemFling = 1

    var currentIndex by remember { mutableStateOf(0) }

    LaunchedEffect(lazyListState.isScrollInProgress) {
        if (!lazyListState.isScrollInProgress) {
            val snappedItem = layoutInfo.currentItem
            if (snappedItem != null) {
                changeCurrentItem(snappedItem.index)
                currentIndex = snappedItem.index
            }
        }
    }

    LazyRow(
        state = lazyListState,
        flingBehavior = rememberSnapperFlingBehavior(
            lazyListState = lazyListState,
            snapIndex = { _, startIndex, targetIndex ->
                targetIndex.coerceIn(startIndex - maxItemFling, startIndex + maxItemFling)
            }
        ),
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(32.dp),
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        items(heros.size) {hero ->
            HeroItem(
                heroCard = heros[hero],
                modifier = Modifier
                    .width(350.dp)
                    .height(550.dp)
                    .graphicsLayer {
                    val offset = layoutInfo.currentItem?.offset
                    val procentOffset: Float = abs(offset!!)/layoutInfo.endScrollOffset.toFloat()
                        Log.e("OFFSET", offset.toString())
                    lerp(
                        start = 0.7f,
                        stop = 1f,
                        fraction = 1f - procentOffset
                    ).also { scale ->
                        scaleX = scale
                        scaleY = scale
                    }
                }
            )
        }

    }

}
