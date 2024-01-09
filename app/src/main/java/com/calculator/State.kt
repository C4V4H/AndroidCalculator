package com.calculator

import java.lang.StringBuilder
import java.util.LinkedList

data class State(
    var number: String = "",
    var operation: Operation? = null,
    val resultExpression: StringBuilder = StringBuilder(""),
    val expression: LinkedList<Any> = LinkedList<Any>()
)


/*
data class State(
    val number1: String = "",
    val number2: String = "",
    val operation: Operation? = null,
    val expression: String = "",
)
*/