package com.finance.services

import com.finance.models.UserCustomDetails
import com.finance.models.UserSys
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class UserCustomDetailService : UserDetailsService {

    @Autowired
    var userService: UserService? = null

    @Transactional
    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(employeename: String): UserDetails {
        val user: UserSys = userService!!.getByUsername(employeename)!!
        return UserCustomDetails(user)
    }
}