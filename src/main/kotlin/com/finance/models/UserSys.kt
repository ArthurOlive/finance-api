package com.finance.models

import java.time.LocalDateTime
import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToMany
import javax.persistence.OneToMany
import javax.persistence.OneToOne

@Entity
class UserSys (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id : Long? = null,
    @OneToOne(cascade = [(CascadeType.ALL)], fetch = FetchType.LAZY)
    @JoinColumn(name = "auth_id", referencedColumnName = "id")
    var auth : Auth? = null,
    @OneToOne(cascade = [(CascadeType.ALL)], fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    var role : Role? = null,
    var createdAt : LocalDateTime = LocalDateTime.now()
)