package com.dynamic.routing.service

import com.dynamic.routing.entity.Employee
import com.dynamic.routing.repository.EmployeeRepository
import org.springframework.stereotype.Service

@Service
class EmployeeService(var employeeRepository: EmployeeRepository) {
    fun getEmployees(): List<Employee> {
        return employeeRepository.findAll()
    }
}