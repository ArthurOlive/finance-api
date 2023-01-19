package com.finance.models

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime
import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.SequenceGenerator

@Entity
class Account (
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id : Long? = null,
        var name : String? = null,
        var number : String? = null,
        var createdAt : LocalDateTime = LocalDateTime.now()
)