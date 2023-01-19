package com.finance.models

import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Color (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id : Long? = null,
    var hex: String? = null,
    var r: Int? = null,
    var g: Int? = null,
    var b: Int? = null,
    var a: Float? = null,
    var createdAt : LocalDateTime = LocalDateTime.now()
)