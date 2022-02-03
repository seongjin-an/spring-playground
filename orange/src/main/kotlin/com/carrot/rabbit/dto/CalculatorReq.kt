package com.carrot.rabbit.dto

import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor

@Data
@AllArgsConstructor
@NoArgsConstructor
class CalculatorReq {
    var x: Int = 0
    var y: Int = 0
}