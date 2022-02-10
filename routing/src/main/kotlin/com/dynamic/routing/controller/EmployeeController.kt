package com.dynamic.routing.controller

import com.dynamic.routing.entity.Employee
import com.dynamic.routing.service.EmployeeService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class EmployeeController(var employeeService: EmployeeService) {
    @GetMapping("employees")
    fun getEmployees(): ResponseEntity<List<Employee>> {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(employeeService.getEmployees())
    }
}