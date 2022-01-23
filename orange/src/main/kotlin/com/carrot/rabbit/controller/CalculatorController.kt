package com.carrot.rabbit.controller

import com.carrot.rabbit.dto.CalculatorReq
import com.carrot.rabbit.dto.CalculatorRes
import com.carrot.rabbit.util.calculator.Calculator
import lombok.RequiredArgsConstructor
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/calculator")
@RequiredArgsConstructor
class CalculatorController(
    private var calculator: Calculator
){
    @GetMapping("sum")
    fun sum(@RequestParam x:Int, @RequestParam y:Int):Int{
        return calculator.sum(x, y)
    }
    @PostMapping("minus")
    fun minus(@RequestBody req:CalculatorReq): CalculatorRes{
        val result: Int = calculator.minus(req.x, req.y)
        val res = CalculatorRes()
        res.result = result
        res.response = CalculatorRes.Body()
        return res
    }
}