@file:Suppress("OPT_IN_IS_NOT_ENABLED")

package gruzdev.artem.marvelapp.screens.selectPersonScreen.views.components

import android.util.Log
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.util.lerp
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import dev.chrisbanes.snapper.ExperimentalSnapperApi
import dev.chrisbanes.snapper.rememberSnapperFlingBehavior
import gruzdev.artem.marvelapp.screens.selectPersonScreen.model.HeroCard
import dev.chrisbanes.snapper.LazyListSnapperLayoutInfo
import dev.chrisbanes.snapper.rememberLazyListSnapperLayoutInfo
import java.lang.Float.max
import java.lang.Float.min
import kotlin.math.abs

@OptIn(ExperimentalSnapperApi::class)
@Composable
fun RowHero(
    heroes: List<HeroCard>,
    isLoading: Boolean,
    endReached: Boolean,
    onValueChange: (Int) -> Unit,
    onclick: (HeroCard) -> Unit,
    onPagingHeroes: () -> Unit
) {
    val lazyListState = rememberLazyListState()
    val layoutInfo: LazyListSnapperLayoutInfo = rememberLazyListSnapperLayoutInfo(lazyListState)
    val maxItemFling = 1

    var currentIndex by remember { mutableStateOf(0) }

    LaunchedEffect(lazyListState.isScrollInProgress) {
        if (!lazyListState.isScrollInProgress) {
            val snappedItem = layoutInfo.currentItem
            if (snappedItem != null) {
                onValueChange(snappedItem.index)
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
        items(heroes.size) { hero ->
            if (hero == heroes.size - 1 && !isLoading && !endReached) onPagingHeroes()
            HeroItem(
                heroCard = heroes[hero],
                modifier = Modifier
                    .width(350.dp)
                    .height(550.dp)
                    .clickable { onclick(heroes[hero]) }
                    .graphicsLayer {
                        val offset: Int? = layoutInfo.currentItem?.offset
                        if (offset != null) {
                            val procentOffset: Float = min(
                                abs(offset) / layoutInfo.endScrollOffset.toFloat(), 1f
                            ) // get global offset
                            val deltaOffset =
                                0.3f * procentOffset // delta offset fot delta scale (1f - 0.7f = 0.3f)
                            if (hero == layoutInfo.currentItem?.index) {
                                val scale = 1f - deltaOffset
                                scaleX = scale
                                scaleY = scale
                            } else if (hero - 1 == layoutInfo.currentItem?.index && offset < 0) {
                                val scale = 0.7f + deltaOffset
                                scaleX = scale
                                scaleY = scale
                            } else if (hero + 1 == layoutInfo.currentItem?.index && offset > 0) {
                                val scale = 0.7f + deltaOffset
                                scaleX = scale
                                scaleY = scale
                            } else {
                                scaleX = 0.7f
                                scaleY = 0.7f
                            }
                        }
                    }
            )
        }
    }
}

