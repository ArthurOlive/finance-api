package com.finance.services

import com.finance.models.UserSys
import com.finance.repositories.UserRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Service
import javax.transaction.Transactional


@Service
class UserService (val userRepository: UserRepository) {

    fun getByUsername(username: String) : UserSys? {
        return userRepository.getByUsername(username)
    }

    @Transactional
    fun create(user: UserSys): UserSys {
        return userRepository.save(user)
    }
}