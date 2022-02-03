package com.carrot.rabbit.util

import com.carrot.rabbit.util.calculator.Calculator
import com.carrot.rabbit.util.calculator.DollarCalculator
import com.carrot.rabbit.util.calculator.MarketApi
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.context.annotation.Import

//@Import(MarketApi::class, DollarCalculator::class)
@SpringBootTest
class DollarCalculatorTest {

    @MockBean
    lateinit var marketApi: MarketApi

    @Autowired
    private lateinit var dollarCalculator: DollarCalculator

    @Autowired
    private lateinit var calculator: Calculator

    @BeforeEach
    fun init2() {
        Mockito.lenient().`when`(marketApi.connect()).thenReturn(3000)
    }

    @Test
    fun dollarCalculatorTest() {
//        Mockito.`when`(marketApi.connect()).thenReturn(3000)
//        dollarCalculator.init()
//        val sum = dollarCalculator.sum(10, 20)
//        val minus = dollarCalculator.minus(10, 10)

        val sum = calculator.sum(10, 20)
        val minus = calculator.minus(10, 10)

        Assertions.assertEquals(90000, sum)
        Assertions.assertEquals(0, minus)
    }

}