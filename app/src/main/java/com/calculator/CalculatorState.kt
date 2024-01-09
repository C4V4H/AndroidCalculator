package com.calculator

import java.lang.StringBuilder
import java.util.LinkedList

data class CalculatorState(
    var numbers: LinkedList<StringBuilder> = LinkedList<StringBuilder>(),
    var operation: Operation? = null,
    val expression: StringBuilder = StringBuilder(""),
)
