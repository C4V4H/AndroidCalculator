package com.calculator

sealed class Operation(val operator: String){
    object Add: Operation("+")
    object Subtract: Operation("-")
    object Multiply: Operation("*")
    object Divide: Operation("/")
}

