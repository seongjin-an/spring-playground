package com.carrot.rabbit.util.calculator

import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Component

@Component
@RequiredArgsConstructor
class WonCalculator(val marketApi: MarketApi): ICalculator {
    var price:Int = 1;

    override fun sum(x: Int, y: Int): Int {
        return (x*price) + (y*price)
    }

    override fun minus(x: Int, y: Int): Int {
        return (x*price) - (y*price)
    }

    override fun init() {
        println("init won calculator")
        this.price = 1
    }

}