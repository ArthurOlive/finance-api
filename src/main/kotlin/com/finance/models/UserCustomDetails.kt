package com.finance.models

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails


class UserCustomDetails : UserDetails {

    private var user: UserSys? = null
    private var username: String? = null
    private var password: String? = null
    private var role: String? = null

    fun getRole(): String? {
        return role
    }

    constructor (user: UserSys) {
        this.user = user
        this.username = user.auth!!.username
        this.password = user.auth!!.password
        role = user.role!!.name
    }

    fun getUser(): UserSys? {
        return user
    }

    override fun getAuthorities(): Collection<GrantedAuthority>? {
        if (role == null) return null
        val auths: MutableCollection<GrantedAuthority> = ArrayList()
        auths.add(SimpleGrantedAuthority("ROLE_$role"))
        return auths
    }

    override fun getPassword(): String? {
        return password
    }

    override fun getUsername(): String? {
        return username
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }

}