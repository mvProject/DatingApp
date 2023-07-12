/*
 * Create by Medvediev Viktor
 * last update: 05.07.23, 14:09
 *
 * Copyright (c) 2023
 *
 */

package com.mvproject.datingapp.ui.components.wheelselector.wheelpicker

import android.util.Log
import androidx.compose.animation.core.DecayAnimationSpec
import androidx.compose.animation.core.calculateTargetValue
import androidx.compose.animation.core.exponentialDecay
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.Velocity
import androidx.compose.ui.unit.dp
import kotlin.math.absoluteValue

interface FWheelPickerContentScope {
    val state: FWheelPickerState
}

interface FWheelPickerContentWrapperScope {
    val state: FWheelPickerState

    @Composable
    fun content(index: Int)
}

@Composable
fun FVerticalWheelPicker(
    count: Int,
    state: FWheelPickerState = rememberFWheelPickerState(),
    modifier: Modifier = Modifier,
    key: ((index: Int) -> Any)? = null,
    itemHeight: Dp = 35.dp,
    unfocusedCount: Int = 1,
    userScrollEnabled: Boolean = true,
    reverseLayout: Boolean = false,
    debug: Boolean = false,
    focus: @Composable () -> Unit = { FWheelPickerFocusVertical() },
    contentWrapper: @Composable FWheelPickerContentWrapperScope.(index: Int) -> Unit = DefaultWheelPickerContentWrapper,
    content: @Composable FWheelPickerContentScope.(index: Int) -> Unit,
) {
    WheelPicker(
        isVertical = true,
        count = count,
        state = state,
        modifier = modifier,
        key = key,
        itemSize = itemHeight,
        unfocusedCount = unfocusedCount,
        userScrollEnabled = userScrollEnabled,
        reverseLayout = reverseLayout,
        debug = debug,
        focus = focus,
        contentWrapper = contentWrapper,
        content = content,
    )
}

@Composable
fun FHorizontalWheelPicker(
    count: Int,
    state: FWheelPickerState = rememberFWheelPickerState(),
    modifier: Modifier = Modifier,
    key: ((index: Int) -> Any)? = null,
    itemWidth: Dp = 35.dp,
    unfocusedCount: Int = 1,
    userScrollEnabled: Boolean = true,
    reverseLayout: Boolean = false,
    debug: Boolean = false,
    focus: @Composable () -> Unit = { FWheelPickerFocusHorizontal() },
    contentWrapper: @Composable FWheelPickerContentWrapperScope.(index: Int) -> Unit = DefaultWheelPickerContentWrapper,
    content: @Composable FWheelPickerContentScope.(index: Int) -> Unit,
) {
    WheelPicker(
        isVertical = false,
        count = count,
        state = state,
        modifier = modifier,
        key = key,
        itemSize = itemWidth,
        unfocusedCount = unfocusedCount,
        userScrollEnabled = userScrollEnabled,
        reverseLayout = reverseLayout,
        debug = debug,
        focus = focus,
        contentWrapper = contentWrapper,
        content = content,
    )
}

@Composable
private fun WheelPicker(
    isVertical: Boolean,
    count: Int,
    state: FWheelPickerState,
    modifier: Modifier,
    key: ((index: Int) -> Any)?,
    itemSize: Dp,
    unfocusedCount: Int,
    userScrollEnabled: Boolean,
    reverseLayout: Boolean,
    debug: Boolean,
    focus: @Composable () -> Unit,
    contentWrapper: @Composable FWheelPickerContentWrapperScope.(index: Int) -> Unit,
    content: @Composable FWheelPickerContentScope.(index: Int) -> Unit,
) {
    require(count >= 0) { "require count >= 0" }
    require(unfocusedCount >= 1) { "require unfocusedCount >= 1" }

    state.debug = debug
    LaunchedEffect(state, count) {
        state.notifyCountChanged(count)
    }

    val nestedScrollConnection = remember(state) {
        WheelPickerNestedScrollConnection(state)
    }.apply {
        this.isVertical = isVertical
        this.itemSizePx = with(LocalDensity.current) { itemSize.roundToPx() }
        this.reverseLayout = reverseLayout
    }

    val totalSize = remember(itemSize, unfocusedCount) {
        itemSize * (unfocusedCount * 2 + 1)
    }

    val contentWrapperScope = remember(state) {
        val contentScope = WheelPickerContentScopeImpl(state)
        FWheelPickerContentWrapperScopeImpl(contentScope)
    }.apply {
        this.content = content
    }

    Box(
        modifier = modifier
            .nestedScroll(nestedScrollConnection)
            .run {
                if (totalSize > 0.dp) {
                    if (isVertical) {
                        height(totalSize).widthIn(40.dp)
                    } else {
                        width(totalSize).heightIn(40.dp)
                    }
                } else {
                    this
                }
            },
        contentAlignment = Alignment.Center,
    ) {

        val lazyListScope: LazyListScope.() -> Unit = {
            repeat(unfocusedCount) {
                item {
                    ItemSizeBox(
                        isVertical = isVertical,
                        itemSize = itemSize,
                    )
                }
            }

            items(
                count = count,
                key = key,
            ) { index ->
                ItemSizeBox(
                    isVertical = isVertical,
                    itemSize = itemSize,
                ) {
                    contentWrapperScope.contentWrapper(index)
                }
            }

            repeat(unfocusedCount) {
                item {
                    ItemSizeBox(
                        isVertical = isVertical,
                        itemSize = itemSize,
                    )
                }
            }
        }

        if (isVertical) {
            LazyColumn(
                state = state.lazyListState,
                horizontalAlignment = Alignment.CenterHorizontally,
                reverseLayout = reverseLayout,
                userScrollEnabled = userScrollEnabled,
                modifier = Modifier.matchParentSize(),
                content = lazyListScope,
            )
        } else {
            LazyRow(
                state = state.lazyListState,
                verticalAlignment = Alignment.CenterVertically,
                reverseLayout = reverseLayout,
                userScrollEnabled = userScrollEnabled,
                modifier = Modifier.matchParentSize(),
                content = lazyListScope,
            )
        }

        ItemSizeBox(
            modifier = Modifier.align(Alignment.Center),
            isVertical = isVertical,
            itemSize = itemSize,
        ) {
            focus()
        }
    }
}

@Composable
private fun ItemSizeBox(
    modifier: Modifier = Modifier,
    isVertical: Boolean,
    itemSize: Dp,
    content: @Composable () -> Unit = { },
) {
    Box(
        modifier
            .run {
                if (isVertical) {
                    height(itemSize)
                } else {
                    width(itemSize)
                }
            },
        contentAlignment = Alignment.Center,
    ) {
        content()
    }
}

private class WheelPickerNestedScrollConnection(
    private val state: FWheelPickerState,
) : NestedScrollConnection {
    var isVertical: Boolean? = null
    var itemSizePx: Int? = null
    var reverseLayout: Boolean? = null

    override fun onPostScroll(
        consumed: Offset,
        available: Offset,
        source: NestedScrollSource
    ): Offset {
        state.synchronizeCurrentIndexSnapshot()
        return super.onPostScroll(consumed, available, source)
    }

    override suspend fun onPreFling(available: Velocity): Velocity {
        val currentIndex = state.synchronizeCurrentIndexSnapshot()
        return if (currentIndex >= 0) {
            val flingItemCount = available.flingItemCount(
                isVertical = isVertical!!,
                itemSize = itemSizePx!!,
                decay = exponentialDecay(2f),
                reverseLayout = reverseLayout!!,
            )

            if (flingItemCount.absoluteValue > 0) {
                state.animateScrollToIndex(currentIndex - flingItemCount)
            } else {
                state.animateScrollToIndex(currentIndex)
            }
            available
        } else {
            super.onPreFling(available)
        }
    }
}

private fun Velocity.flingItemCount(
    isVertical: Boolean,
    itemSize: Int,
    decay: DecayAnimationSpec<Float>,
    reverseLayout: Boolean,
): Int {
    if (itemSize <= 0) return 0
    val velocity = if (isVertical) y else x
    val targetValue = decay.calculateTargetValue(0f, velocity)
    val flingItemCount = (targetValue / itemSize).toInt()
    return if (reverseLayout) -flingItemCount else flingItemCount
}

private class WheelPickerContentScopeImpl(
    override val state: FWheelPickerState,
) : FWheelPickerContentScope

private class FWheelPickerContentWrapperScopeImpl(
    private val contentScope: FWheelPickerContentScope
) : FWheelPickerContentWrapperScope {
    lateinit var content: @Composable FWheelPickerContentScope.(index: Int) -> Unit

    override val state: FWheelPickerState get() = contentScope.state

    @Composable
    override fun content(index: Int) {
        contentScope.content(index)
    }
}

internal inline fun logMsg(debug: Boolean, block: () -> String) {
    if (debug) {
        Log.i("FWheelPicker", block())
    }
}

/*

@Composable
private fun SampleDefault() {
    FVerticalWheelPicker(
        modifier = Modifier.width(60.dp),
        // Specified item count.
        count = 50,
    ) { index ->
        Text(index.toString())
    }
}

@Composable
fun SampleCustomItemSize() {
    FVerticalWheelPicker(
        modifier = Modifier.width(60.dp),
        count = 50,
        // Specified item height.
        itemHeight = 60.dp,
    ) {
        Text(it.toString())
    }
}

@Composable
fun SampleCustomUnfocusedCount() {
    FVerticalWheelPicker(
        modifier = Modifier.width(60.dp),
        count = 50,
        // Specified unfocused count.
        unfocusedCount = 2,
    ) {
        Text(it.toString())
    }
}

@Composable
private fun SampleCustomDivider() {
    FVerticalWheelPicker(
        modifier = Modifier.width(60.dp),
        count = 50,
        focus = {
            // Custom divider.
            FWheelPickerFocusVertical(dividerColor = Color.Red, dividerSize = 2.dp)
        },
    ) {
        Text(it.toString())
    }
}

@Composable
private fun SampleCustomFocus() {
    FVerticalWheelPicker(
        modifier = Modifier.width(60.dp),
        count = 50,
        focus = {
            // Custom focus.
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .border(width = 1.dp, color = Color.Gray)
            )
        },
    ) {
        Text(it.toString())
    }
}

@Composable
private fun SampleScrollToIndex() {
    // Specified initial index.
    val state = rememberFWheelPickerState(10)
    LaunchedEffect(state) {
        delay(2000)
        // Scroll to index.
        state.animateScrollToIndex(20)
    }

    FVerticalWheelPicker(
        modifier = Modifier.width(60.dp),
        count = 50,
        // state
        state = state,
    ) {
        Text(it.toString())
    }
}

@Composable
private fun SampleObserveIndex() {
    val state = rememberFWheelPickerState()
    FVerticalWheelPicker(
        modifier = Modifier.width(60.dp),
        count = 50,
        state = state,
        debug = true,
    ) {
        Text(it.toString())
    }

    // Observe currentIndex.
    LaunchedEffect(state) {
        snapshotFlow { state.currentIndex }
            .collect {
                logMsg { "currentIndex ${state.currentIndex}" }
            }
    }

    // Observe currentIndexSnapshot.
    LaunchedEffect(state) {
        snapshotFlow { state.currentIndexSnapshot }
            .collect {
                logMsg { "currentIndexSnapshot ${state.currentIndexSnapshot}" }
            }
    }
}

@Composable
private fun SampleCustomContentWrapper() {
    FVerticalWheelPicker(
        modifier = Modifier.width(60.dp),
        count = 50,
        contentWrapper = { index ->
            if (state.currentIndexSnapshot == index) {
                content(index)
            } else {
                // Modify content if it is not in focus.
                Box(
                    modifier = Modifier
                        .rotate(90f)
                        .alpha(0.5f)
                ) {
                    content(index)
                }
            }
        }
    ) {
        Text(it.toString())
    }
}

@Composable
private fun SampleReverseLayout() {
    FVerticalWheelPicker(
        modifier = Modifier.width(60.dp),
        count = 50,
        // Reverse layout.
        reverseLayout = true,
    ) {
        Text(it.toString())
    }
}
 */