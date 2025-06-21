package com.assignment.hometab.tables.domain.model


data class Order(
    var id: Int?=null,
    var image: String?,

    var name: String?,
    var description: String?,

    var price: Double?,

)
