package com.calculator

data class State(
    val number1: String = "",
    val number2: String = "",
    val operation: Operation? = null,
)
