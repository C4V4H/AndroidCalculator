package com.calculator

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.calculator.ui.theme.LightGray
import com.calculator.ui.theme.MediumGray
import com.calculator.ui.theme.Orange

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun Calculator(
    state: State,
    modifier: Modifier = Modifier,
    buttonSpacing: Dp = 8.dp,
    onAction: (Action) -> Unit
) {

    val textStyle1 by remember {
        mutableStateOf(
            TextStyle(
                color = Color.White,
                fontSize = 40.sp,
                fontWeight = FontWeight.Light,
                lineHeight = 40.sp,
                textAlign = TextAlign.Start,
            )
        )
    }
    val textStyle2 by remember {
        mutableStateOf(
            TextStyle(
                color = Color.White,
                fontSize = 60.sp,
                fontWeight = FontWeight.Light,
                lineHeight = 80.sp,
                textAlign = TextAlign.End,
            )
        )
    }


    Box(
        modifier = modifier,
        contentAlignment = Alignment.BottomCenter
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            verticalArrangement = Arrangement.spacedBy(buttonSpacing)
        ) {
            Text(
                text = state.expression,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 30.dp)
                    .height(50.dp)
                    .horizontalScroll(
                    state = rememberScrollState(),
                    reverseScrolling = true,
                ),
                maxLines = 1,
                softWrap = false,
                style = textStyle1,
            )

            Text(
                text = state.number1 + (state.operation?.operator ?: "") + state.number2,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 30.dp)
                    .height(80.dp)
                .horizontalScroll(
                    state = rememberScrollState(),
                    reverseScrolling = true,
                ),
                maxLines = 1,
                softWrap = false,
                style = textStyle2,
            )





            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
            ) {
                CalculartorButton(
                    text = "AC",
                    modifier = Modifier
                        .background(LightGray)
                        .aspectRatio(2f)
                        .weight(2f),
                    onClick = {
                        onAction(Action.Clear)
                    }
                ) {}

                CalculartorButton(
                    text = "Del",
                    modifier = Modifier
                        .background(LightGray)
                        .aspectRatio(1f)
                        .weight(1f),
                    onClick = {
                        onAction(Action.Delete)
                    }
                ) {}

                CalculartorButton(
                    text = "/",
                    modifier = Modifier
                        .background(Orange)
                        .aspectRatio(1f)
                        .weight(1f),
                    onClick = {
                        onAction(Action.Operation(Operation.Divide))
                    }
                ) {}
            }


            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
            ) {
                CalculartorButton(
                    text = "7",
                    modifier = Modifier
                        .background(MediumGray)
                        .aspectRatio(1f)
                        .weight(1f),
                    onClick = {
                        onAction(Action.Number(7))
                    }
                ) {}

                CalculartorButton(
                    text = "8",
                    modifier = Modifier
                        .background(MediumGray)
                        .aspectRatio(1f)
                        .weight(1f),
                    onClick = {
                        onAction(Action.Number(8))
                    }
                ) {}

                CalculartorButton(
                    text = "9",
                    modifier = Modifier
                        .background(MediumGray)
                        .aspectRatio(1f)
                        .weight(1f),
                    onClick = {
                        onAction(Action.Number(9))
                    }
                ) {}

                CalculartorButton(
                    text = "*",
                    modifier = Modifier
                        .background(Orange)
                        .aspectRatio(1f)
                        .weight(1f),
                    onClick = {
                        onAction(Action.Operation(Operation.Multiply))
                    }
                ) {}
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
            ) {
                CalculartorButton(
                    text = "4",
                    modifier = Modifier
                        .background(MediumGray)
                        .aspectRatio(1f)
                        .weight(1f),
                    onClick = {
                        onAction(Action.Number(4))
                    }
                ) {}

                CalculartorButton(
                    text = "5",
                    modifier = Modifier
                        .background(MediumGray)
                        .aspectRatio(1f)
                        .weight(1f),
                    onClick = {
                        onAction(Action.Number(5))
                    }
                ) {}

                CalculartorButton(
                    text = "6",
                    modifier = Modifier
                        .background(MediumGray)
                        .aspectRatio(1f)
                        .weight(1f),
                    onClick = {
                        onAction(Action.Number(6))
                    }
                ) {}

                CalculartorButton(
                    text = "-",
                    modifier = Modifier
                        .background(Orange)
                        .aspectRatio(1f)
                        .weight(1f),
                    onClick = {
                        onAction(Action.Operation(Operation.Subtract))
                    }
                ) {}
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
            ) {
                CalculartorButton(
                    text = "1",
                    modifier = Modifier
                        .background(MediumGray)
                        .aspectRatio(1f)
                        .weight(1f),
                    onClick = {
                        onAction(Action.Number(1))
                    }
                ) {}

                CalculartorButton(
                    text = "2",
                    modifier = Modifier
                        .background(MediumGray)
                        .aspectRatio(1f)
                        .weight(1f),
                    onClick = {
                        onAction(Action.Number(2))
                    }
                ) {}

                CalculartorButton(
                    text = "3",
                    modifier = Modifier
                        .background(MediumGray)
                        .aspectRatio(1f)
                        .weight(1f),
                    onClick = {
                        onAction(Action.Number(3))
                    }
                ) {}

                CalculartorButton(
                    text = "+",
                    modifier = Modifier
                        .background(Orange)
                        .aspectRatio(1f)
                        .weight(1f),
                    onClick = {
                        onAction(Action.Operation(Operation.Add))
                    }
                ) {}
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
            ) {
                CalculartorButton(
                    text = "0",
                    modifier = Modifier
                        .background(MediumGray)
                        .aspectRatio(2f)
                        .weight(2f),
                    onClick = {
                        onAction(Action.Number(0))
                    }
                ) {}

                CalculartorButton(
                    text = ".",
                    modifier = Modifier
                        .background(LightGray)
                        .aspectRatio(1f)
                        .weight(1f),
                    onClick = {
                        onAction(Action.Decimal)
                    }
                ) {}

                CalculartorButton(
                    text = "=",
                    modifier = Modifier
                        .background(Orange)
                        .aspectRatio(1f)
                        .weight(1f),
                    onClick = {
                        onAction(Action.Calculate)
                    }
                ) {}
            }
        }
    }
}