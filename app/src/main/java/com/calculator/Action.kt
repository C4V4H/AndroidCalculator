package com.calculator

sealed class Action{
    data class Number(val number: kotlin.Number): Action()
    object Clear: Action()
    object Delete: Action()
    object Decimal: Action()
    object Calculate: Action()
    data class Operation(val operation: com.calculator.Operation): Action()
}