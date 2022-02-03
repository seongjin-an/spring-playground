package com.carrot.rabbit.util.calculator

import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Component

@Component
@RequiredArgsConstructor
class DollarCalculator(val marketApi: MarketApi) : ICalculator {
    var price: Int = 1;

    override fun init() {
        println("init dollar calculator")
        this.price = marketApi.connect()
    }

    override fun sum(x: Int, y: Int): Int {
        return (x * price) + (y * price)
    }

    override fun minus(x: Int, y: Int): Int {
        return (x * price) - (y * price)
    }

}