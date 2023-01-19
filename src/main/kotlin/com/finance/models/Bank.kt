package com.finance.models

import java.time.LocalDateTime
import javax.persistence.*

@Entity
class Bank (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id : Long? = null,
    var name : String? = null,
    var logo : String? = null,
    @OneToOne(cascade = [(CascadeType.ALL)], fetch = FetchType.LAZY)
    @JoinColumn(name = "color_id", referencedColumnName = "id")
    var color: Color?,
    var createdAt : LocalDateTime = LocalDateTime.now()
)
