package com.carrot.rabbit.util.calculator

interface ICalculator {
    fun sum(x: Int, y: Int): Int
    fun minus(x: Int, y: Int): Int
    fun init();
}