package com.calculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.calculator.ui.theme.CalculatorTheme
import androidx.lifecycle.viewmodel.compose.viewModel
import java.lang.StringBuilder

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculatorTheme {
                GUI()
            }
        }
    }
}

@Composable
fun GUI() {
    val viewModel = viewModel<CalculatorViewModel>()
    val buttonSpacing = 8.dp

    val exp = StringBuilder("")
    viewModel.expression.forEach {
        exp.append("$it ")
    }

    Calculator(
        state = viewModel.state,
        expression = exp.toString(),
        onAction = viewModel::onAction,
        buttonSpacing = buttonSpacing,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color.DarkGray)
            .padding(16.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CalculatorTheme {
        GUI()
    }
}


/*
var count by remember {
    mutableStateOf("a")
}
var c  by remember {
    mutableStateOf(0)
}
Column (
    modifier = Modifier
        .fillMaxSize(),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
) {
    Text(
        text = count.toString(),
        modifier = Modifier
            .padding(10.dp),
        fontSize = 20.sp,
    )
    Button(
        onClick = {
            c++
            count += ('a' + c)
        },
        modifier = Modifier.padding(10.dp),
    ) {
        Text("Click me!")
    }
}
 */