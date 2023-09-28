package com.calculator

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.isUnspecified
import androidx.compose.ui.unit.sp

@Composable
fun CalculatorText(
    text: String,
    style: TextStyle = MaterialTheme.typography.bodyMedium,
    modifier: Modifier = Modifier,
    maxSize: TextUnit = 80.sp,
    minSize: TextUnit = 10.sp,
    softWrap: Boolean = false,
    maxLines: Int = 1,
) {
    var resizedTextStyle by remember {
        mutableStateOf(style)
    }
    var shouldDraw by remember {
        mutableStateOf(false)
    }
    var resized by remember {
        mutableStateOf(false)
    }
    val defaultfontsize = MaterialTheme.typography.bodyMedium.fontSize
    Text(
        text = text,
        modifier = modifier.drawWithContent {
            if (shouldDraw)
                drawContent()
        },
        style = resizedTextStyle,
        softWrap = softWrap,
        maxLines = maxLines,
        onTextLayout = { result ->
            if (style.fontSize.isUnspecified)
                resizedTextStyle = resizedTextStyle.copy(fontSize = defaultfontsize)

            if (result.didOverflowWidth) {
                if (resizedTextStyle.fontSize >= minSize) {
                    resizedTextStyle =
                        resizedTextStyle.copy(fontSize = resizedTextStyle.fontSize * 0.9)
                    resized = false
                } else
                    resizedTextStyle.copy(fontSize = minSize)
            } else {
                shouldDraw = true
                if (resized) {
                    resizedTextStyle = if (resizedTextStyle.fontSize < maxSize)
                        resizedTextStyle.copy(fontSize = resizedTextStyle.fontSize * 1.1)
                    else
                        resizedTextStyle.copy(fontSize = maxSize)
                }
                resized = true
            }
        }

    )
}