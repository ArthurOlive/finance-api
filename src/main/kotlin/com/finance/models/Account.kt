package com.finance.models

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity
class Account (
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id : Long? = null,
        var name : String? = null,

        var agency : String? = null,
        var number : String? = null,

        @OneToOne(cascade = [(CascadeType.ALL)], fetch = FetchType.LAZY)
        @JoinColumn(name = "bank_id", referencedColumnName = "id")
        var bank: Bank?,

        @OneToMany(mappedBy = "account")
        var cards : List<Card>?,

        @OneToOne(cascade = [(CascadeType.ALL)], fetch = FetchType.LAZY)
        @JoinColumn(name = "user_sys_id", referencedColumnName = "id")
        var userSys: UserSys?,
        var createdAt : LocalDateTime = LocalDateTime.now()
)