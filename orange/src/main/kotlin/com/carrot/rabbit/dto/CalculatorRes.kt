package com.carrot.rabbit.dto

import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor

@Data
@NoArgsConstructor
@AllArgsConstructor
class CalculatorRes {
    var result:Int = 0
    var response: Body? = null

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    class Body(
        var resultCode:String = "OK"
    ){

    }
}