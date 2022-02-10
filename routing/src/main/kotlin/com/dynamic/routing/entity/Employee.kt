package com.dynamic.routing.entity

import javax.persistence.*

@Entity
@Table(name = "employee", schema = "api")
class Employee (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,

    var name: String,

    var branch: String
)