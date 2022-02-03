package com.carrot.rabbit.controller

import com.carrot.rabbit.dto.CalculatorReq
import com.carrot.rabbit.util.calculator.Calculator
import com.carrot.rabbit.util.calculator.DollarCalculator
import com.carrot.rabbit.util.calculator.MarketApi
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.context.annotation.Import
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@WebMvcTest(CalculatorController::class)
@AutoConfigureWebMvc
@Import(Calculator::class, DollarCalculator::class)
class CalculatorApiControllerTest {
    @MockBean
    private lateinit var marketApi: MarketApi

    @Autowired
    private lateinit var mockMvc: MockMvc

    @BeforeEach
    fun init2() {
        Mockito.lenient().`when`(marketApi.connect()).thenReturn(3000)
    }

    @Test
    fun sumTest() {
        //http://localhost:8080/api/calculator/sum?x=10&y=20
        mockMvc.perform(
            MockMvcRequestBuilders.get("http://localhost:8080/api/calculator/sum")
                .queryParam("x", "10")
                .queryParam("y", "20")
        ).andExpect(
            MockMvcResultMatchers.status().isOk
        ).andExpect(
            MockMvcResultMatchers.content().string("90000")
        ).andDo(MockMvcResultHandlers.print())
    }

    @Test
    fun minusTest() {
        val req = CalculatorReq()
        req.x = 10
        req.y = 10

        val json = ObjectMapper().writeValueAsString(req)

        mockMvc.perform(
            MockMvcRequestBuilders.post("http://localhost:8080/api/calculator/minus")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
        ).andExpect(
            MockMvcResultMatchers.status().isOk
        ).andExpect(
            MockMvcResultMatchers.jsonPath("$.result").value("0")
        ).andExpect(
            MockMvcResultMatchers.jsonPath("$.response.resultCode").value("OK")
        ).andDo(MockMvcResultHandlers.print())
    }
}