package org.example

import java.lang.Exception
import java.security.InvalidParameterException
import java.util.*

object ExpressionSolver {
    private var coda: Queue<Any> = LinkedList() ///.add() .size .isEmpty() .isNotEmpty() .peek() .poll()
    private var stack: Stack<Any> = Stack() //.push() .size .isEmpty .isNotEmpty() .peek() .pop()

    fun solve(expression: ArrayList<Any>): Number {
        parseExpression(expression)
        while (coda.isNotEmpty()) {
            when (coda.peek()) {
                is Number -> stack.push(coda.poll())
                is Char -> calc(coda.poll() as Char)
            }
        }
        return stack.pop() as Number
    }

    private fun calc(operator: Char) {
        val arg2: Double = (stack.pop() as Number).toDouble()
        val arg1: Double = (stack.pop() as Number).toDouble()

        when (operator) {
            '+' -> stack.push(arg1 + arg2)
            '-' -> stack.push(arg1 - arg2)
            '*' -> stack.push(arg1 * arg2)
            '/' -> {
                if (arg2 != 0.0)
                    stack.push(arg1 / arg2)
                else
                    throw ArithmeticException("/ by zero")
            }
        }

    }

    private fun parseExpression(expression: ArrayList<Any>) {
        coda = LinkedList()
        stack = Stack()
        for (i in expression) {
            when (i) {
                is Number -> coda.add(i)
                is Char -> parseOperator(i)
                else -> throw InvalidParameterException("Expression can only contains 'Number' and 'Char'")
            }
        }
        while (stack.isNotEmpty())
            coda.add(stack.pop())
    }

    private fun parseOperator(c: Char) {
        if (stack.isEmpty() || (getOperatorGrade(stack.peek()) < getOperatorGrade(c) && getOperatorGrade(c) != 3)) {
            stack.push(c)
        } else if (getOperatorGrade(stack.peek()) > getOperatorGrade(c)) {
            while (stack.isNotEmpty() && (getOperatorGrade(stack.peek()) != 2)) {
                coda.add(stack.pop())
            }
            stack.push(c)
        } else if (getOperatorGrade(c) == 3) {
            while (getOperatorGrade(stack.peek()) != 2)
                coda.add(stack.pop())
            stack.pop()
        }
    }

    private fun getOperatorGrade(c: Any): Int {
        return when (c) {
            '+', '-' -> 0
            '*', '/' -> 1
            '(' -> 2
            ')' -> 3
            else -> {
                -1
            }
        }
    }
}