package com.calculator

import android.annotation.SuppressLint
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import java.util.LinkedList
import kotlin.text.StringBuilder

@SuppressLint("MutableCollectionMutableState")
class CalculatorViewModel : ViewModel() {

    var state by mutableStateOf(State())
        private set

    var expression by mutableStateOf(LinkedList<Any>())

    private var canCalculate: Boolean = false

    fun onAction(action: Action) {
        /*print(
            """
            expression: ${expression},
            number: ${state.number},
            resultExpression: ${state.resultExpression},
            operation: ${state.operation}
        """.trimIndent()
        )*/
        when (action) {
            is Action.Number -> enterNumber(action.number)
            is Action.Decimal -> enterDecimal()
            is Action.Clear -> state = State()
            is Action.Operation -> enterOperation(action.operation)
            is Action.Calculate -> performCalculation()
            is Action.Delete -> performDeletion()
        }
    }

    private fun enterNumber(number: Number) {
        state.operation = null
        if (state.number.length >= MAX_NUM_LENGTH)
            return

        state = state.copy(
            number = state.number + number
        )

        if (expression.lastIndex != -1)
            if (expression[expression.lastIndex] !is Number)
                expression.add(number)
            else
                expression[expression.lastIndex] = (expression[expression.lastIndex].toString() + number).toInt()
        else
            expression.add(number)

        if (expression.size > 1)
            canCalculate = true

        return
    }

//    private fun enterNumber(number: Number) {
//        if(state.operation == null) {
//            if(state.number1.length >= MAX_NUM_LENGTH) {
//                return
//            }
//            state = state.copy(
//                number1 = state.number1 + number
//            )
//
//            return
//        }
//        if(state.number2.length >= MAX_NUM_LENGTH) {
//            return
//        }
//        state = state.copy(
//            number2 = state.number2 + number
//        )
//    }


    private fun enterDecimal() {
        val num = state.number
        if (state.operation == null && !num.contains(".") && num.isNotBlank()) {
            state = state.copy(
                number = state.number + "."
            )
            state = state.copy()
            return
        }
    }

//    private fun enterDecimal() {
//        if(state.operation == null && !state.number1.contains(".") && state.number1.isNotBlank()) {
//            state = state.copy(
//                number1 = state.number1 + "."
//            )
//            return
//        } else if(!state.number2.contains(".") && state.number2.isNotBlank()) {
//            state = state.copy(
//                number2 = state.number2 + "."
//            )
//        }
//    }


    private fun enterOperation(operation: Operation) {
        val number = state.number.toDoubleOrNull()
        if (state.number.isNotBlank() && number != null) {

            if (expression[expression.lastIndex] !is Number)
                expression[expression.lastIndex] = operation.operator[0]
            else
                expression.add(operation.operator[0])

            state = state.copy(
                number = "",
                operation = operation,
                resultExpression = StringBuilder("")
            )

            canCalculate = false
        }
    }

//    private fun enterOperation(operation: Operation) {
//        if (state.number1.isNotBlank()){
//            state = state.copy(operation = operation, expression = "")
//        }
//    }

    private fun performCalculation() {
        val num = state.number.toDoubleOrNull()
        if (num != null) {
            val result = ExpressionSolver.solve(expression)

            val resExp = StringBuilder("")

            expression.forEach {
                resExp.append("$it ")
            }
            resExp.append(" =")

            state = state.copy(
                number = result.toString().take(10),
                operation = null,
                resultExpression = resExp
            )

            expression = LinkedList<Any>()
            expression.add(result)
        }
    }


//    private fun performCalculation() {
//        val number1 = state.number1.toDoubleOrNull()
//        val number2 = state.number2.toDoubleOrNull()
//        if(number1 != null && number2 != null) {
//            val result = when(state.operation) {
//                is Operation.Add -> number1 + number2
//                is Operation.Subtract -> number1 - number2
//                is Operation.Multiply -> number1 * number2
//                is Operation.Divide -> number1 / number2
//                null -> return
//            }
//            state = state.copy(
//                number1 = result.toString().take(10),
//                number2 = "",
//                operation = null,
//                expression = state.number1 + " " + (state.operation?.operator ?: "") + " " + state.number2 + " ="
//            )
//        }
//    }

    private fun performDeletion() {
        if (expression[expression.lastIndex] is Number) {
            expression[expression.lastIndex] =
                expression[expression.lastIndex].toString().dropLast(1)
            state = state.copy(
                number = state.number.dropLast(1)
            )
        } else {
            expression.dropLast(1)
            state = state.copy(
                operation = null
            )
        }
    }

//    private fun performDeletion() {
//        when {
//            state.number2.isNotBlank() -> state = state.copy(
//                number2 = state.number2.dropLast(1)
//            )
//            state.operation != null -> state = state.copy(
//                operation = null
//            )
//            state.number1.isNotBlank() -> state = state.copy(
//                number1 = state.number1.dropLast(1)
//            )
//        }
//    }

    companion object {
        private const val MAX_NUM_LENGTH = 8
    }
}


/*
    var state by mutableStateOf(State())
        private set

    fun onAction(action: Action) {
        when (action) {
            is Action.Number -> enterNumber(action.number)
            is Action.Decimal -> enterDecimal()
            is Action.Clear -> state = State()
            is Action.Operation -> enterOperation(action.operation)
            is Action.Calculate -> performCalculation()
            is Action.Delete -> performDeletion()
        }
    }





    companion object {
        private const val MAX_NUM_LENGTH = 8
    }

*/