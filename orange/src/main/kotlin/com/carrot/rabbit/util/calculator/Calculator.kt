package com.carrot.rabbit.util.calculator

import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Component

@Component("calculator")
@RequiredArgsConstructor
class Calculator(
    val iCalculator: ICalculator
){
    fun sum(x:Int, y:Int):Int{
        this.iCalculator.init()
        return this.iCalculator.sum(x, y)
    }
    fun minus(x: Int, y: Int): Int{
        this.iCalculator.init()
        return this.iCalculator.minus(x, y)
    }
}