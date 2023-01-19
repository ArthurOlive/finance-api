package com.finance.models

import java.time.LocalDateTime
import javax.persistence.*

@Entity
class Card (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id : Long? = null,
    var number : String? = null,
    var creditLimit: String? = null,
    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    var account: Account?,
    var createdAt : LocalDateTime = LocalDateTime.now()
)