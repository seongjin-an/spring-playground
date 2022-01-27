package com.carrot.rabbit.controller

import com.carrot.rabbit.dto.CalculatorReq
import com.carrot.rabbit.dto.CalculatorRes
import com.carrot.rabbit.util.calculator.Calculator
import com.carrot.rabbit.util.calculator.DollarCalculator
import com.carrot.rabbit.util.calculator.WonCalculator
import lombok.RequiredArgsConstructor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/calculator")
@RequiredArgsConstructor
class CalculatorController(
    private var calculator: Calculator
){
    @Autowired
    private lateinit var wonCalculator: WonCalculator
    @Autowired
    private lateinit var dollarCalculator: DollarCalculator

    @GetMapping("sum")
    fun sum(@RequestParam x:Int, @RequestParam y:Int):Int{
        calculator.setCalculator(wonCalculator)
        calculator.setCalculator(dollarCalculator)
        calculator.setCalculator(wonCalculator)
        return calculator.sum(x, y)
    }
    @PostMapping("minus")
    fun minus(@RequestBody req:CalculatorReq): CalculatorRes{
        calculator.setCalculator(dollarCalculator)
        val result: Int = calculator.minus(req.x, req.y)
        val res = CalculatorRes()
        res.result = result
        res.response = CalculatorRes.Body()
        return res
    }
}