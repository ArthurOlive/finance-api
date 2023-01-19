package com.finance.repositories

import com.finance.models.UserSys
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository


@Repository
interface UserRepository : CrudRepository<UserSys, Long> {

    @Query("SELECT u FROM UserSys u WHERE u.auth.username =:username")
    fun getByUsername(@Param("username") username : String) : UserSys?

}